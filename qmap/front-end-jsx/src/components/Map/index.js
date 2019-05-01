import React, { Component } from "react";
import L from "leaflet";
import Popup from "../Popup";
import "leaflet/dist/leaflet.css";
import { renderToStaticMarkup } from "react-dom/server";
export default class QMap extends Component {
  componentDidMount() {
    const map = L.map("image-map", {
      minZoom: 1.5,
      maxZoom: 4,
      center: [0, 0],
      zoom: 1,
      crs: L.CRS.Simple,
      attributionControl: false
    });
    L.control
      .attribution({
        prefix: false
      })
      .addAttribution("UQ QAAFI")
      .addTo(map);
    const w = 1280 * 2;
    const h = 806 * 2;
    const url = "/maps/somemap.svg";
    const southWest = map.unproject([0, h], map.getMaxZoom() - 1);
    const northEast = map.unproject([w, 0], map.getMaxZoom() - 1);
    const bounds = new L.LatLngBounds(southWest, northEast);
    L.imageOverlay(url, bounds).addTo(map);
    map.setMaxBounds(bounds);
    map.on("click", addMarker);
    const customPin = L.divIcon({
      className: "location-pin",
      html:
        '<img src="https://static.robinpowered.com/roadshow/robin-avatar.png"><div class="pin"></div><div class="pulse"></div>',
      iconSize: [30, 30],
      iconAnchor: [18, 30]
    });
    const popup = L.popup({
      closeButton: true,
      autoClose: true,
      className: "custom-popup" // classname for the popup acting like a splash screen
    }).setContent(renderToStaticMarkup(<Popup />));
    function addMarker(e) {
      console.log(renderToStaticMarkup(<Popup />));
      const newMarker = new L.marker(e.latlng, {
        icon: customPin
      }).addTo(map);
      newMarker.bindPopup(popup);
    }
  }
  render() {
    return <div id="image-map" />;
  }
}
