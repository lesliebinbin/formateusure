(ns au.edu.uq.utils.excel-reader
  (:require [dk.ative.docjure.spreadsheet :as ss]
            [cheshire.core :as json]
            [clojure.string :as str]))

(declare process-sheet)
(declare remove-nil-from-map)

(defn read-excel
  ([filename] (->>
                (ss/load-workbook-from-resource (str "excels/" filename))
                ss/sheet-seq
                (mapv process-sheet)
                json/generate-string
                ))
  ([filename sheetname]
   (->> (ss/load-workbook-from-resource (str "excels/" filename))
        (ss/select-sheet sheetname)
        process-sheet
        json/generate-string
        ))
  )

(defn process-sheet [sheet]
  (let [name (.getSheetName sheet)
        rows (->> sheet
                  ss/row-seq
                  (remove nil?)
                  (map ss/cell-seq)
                  (map #(map ss/read-cell %))
                  )
        header (->> rows
                    first
                    (map #(if (nil? %) % (str/lower-case %)))
                    )
        contents (rest rows)
        ]
    (as-> contents <>
          (mapv #(zipmap header %) <>)
          (for [record <>] (->> record
                                remove-nil-from-map
                                (into {})))
          (remove empty? <>)
          (assoc {} name <>)
          )

    ))

(defn remove-nil-from-map [some-map]
  (->> (seq some-map)
       (remove #(.contains % nil))
       (into {})
       ))
