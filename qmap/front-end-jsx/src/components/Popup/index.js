import React from "react";
import PropTypes from "prop-types";
import classNames from "classnames";
import { withStyles } from "@material-ui/core/styles";
import MenuItem from "@material-ui/core/MenuItem";
import TextField from "@material-ui/core/TextField";

const styles = theme => ({
  container: {
    display: "flex",
    flexWrap: "wrap"
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200
  },
  dense: {
    marginTop: 19
  },
  menu: {
    width: 200
  }
});

const currencies = [
  {
    value: "USD",
    label: "$"
  },
  {
    value: "EUR",
    label: "€"
  },
  {
    value: "BTC",
    label: "฿"
  },
  {
    value: "JPY",
    label: "¥"
  }
];

class TextFields extends React.Component {
  state = {
    name: "Cat in the Hat",
    age: "",
    multiline: "Controlled",
    currency: "EUR"
  };

  handleChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  render() {
    const { classes } = this.props;

    return (
      <form className={classes.container} noValidate autoComplete="off">
        <TextField
          id="standard-name"
          label="Name"
          className={classes.textField}
          value={this.state.name}
          onChange={this.handleChange("name")}
          margin="normal"
        />

        <TextField
          id="standard-uncontrolled"
          label="email"
          defaultValue="foo"
          className={classes.textField}
          margin="normal"
        />

        <TextField
          required
          id="standard-required"
          label="Porn hub"
          defaultValue="Hello World"
          className={classes.textField}
          margin="normal"
        />

        <TextField
          error
          id="standard-error"
          label="Something funny"
          defaultValue="Hello World"
          className={classes.textField}
          margin="normal"
        />

        <TextField
          disabled
          id="standard-disabled"
          label="WTF"
          defaultValue="Hello World"
          className={classes.textField}
          margin="normal"
        />

        <TextField
          id="standard-password-input"
          label="Suck..."
          className={classes.textField}
          type="password"
          autoComplete="current-password"
          margin="normal"
        />
      </form>
    );
  }
}

TextFields.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(TextFields);
