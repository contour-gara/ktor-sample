import {Button} from "@mui/material";
import {Link} from "react-router";

function About() {
  return (
    <>
      <h1>About</h1>
      {/* SPA で /health を定義していないため何も表示されないが、リロードすると ktor の health が返る */}
      <Button variant="outlined" size="large" component={Link} to="/health">
        health
      </Button>
    </>
  )
}

export default About
