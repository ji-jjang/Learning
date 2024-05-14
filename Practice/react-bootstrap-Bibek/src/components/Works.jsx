import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import imgWorks1 from "../assets/images/img2.jpg";
import imgWorks2 from "../assets/images/img3.jpg";
import imgWorks3 from "../assets/images/img4.jpg";
import imgWorks4 from "../assets/images/img5.jpg";
import imgWorks5 from "../assets/images/img6.jpg";
import imgWorks6 from "../assets/images/img7.jpg";
import imgWorks7 from "../assets/images/img8.jpg";
import imgWorks8 from "../assets/images/img9.jpg";
import "../css/work.css";

const worksData = [
  {
    id: 1,
    link: "https://www.google.com",
    image: imgWorks1,
    title: "Lonely Path",
    subtitle: "Web Design",
  },
  {
    id: 2,
    link: "https://www.google.com",
    image: imgWorks2,
    title: "Photographer Girl",
    subtitle: "Branding",
  },
  {
    id: 3,
    link: "https://www.google.com",
    image: imgWorks3,
    title: "The Difference",
    subtitle: "Web Design",
  },
  {
    id: 4,
    link: "https://www.google.com",
    image: imgWorks4,
    title: "Nature Patterns",
    subtitle: "Branding",
  },
  {
    id: 5,
    link: "https://www.google.com",
    image: imgWorks5,
    title: "The Difference",
    subtitle: "Photography",
  },
  {
    id: 6,
    link: "https://www.google.com",
    image: imgWorks6,
    title: "Winter Sonata",
    subtitle: "Web Design",
  },
  {
    id: 7,
    link: "https://www.google.com",
    image: imgWorks7,
    title: "Lonely Path",
    subtitle: "Branding",
  },
  {
    id: 8,
    link: "https://www.google.com",
    image: imgWorks8,
    title: "Appreciation",
    subtitle: "Photography",
  },
  {
    id: 9,
    link: "https://www.google.com",
    image: imgWorks1,
    title: "Happy Days",
    subtitle: "Web Design",
  },
];

export default function Works() {
  return (
    <section id="works" className="block works-block">
      <Container fluid>
        <div className="title-holder">
          <h2>Our works</h2>
          <div className="subtitle">our awesome works</div>
        </div>
        <Row className="portfolio-list">
          {worksData.map((works) => {
            return (
              <Col sm={4} key={works.id}>
                <div className="portfolio-wrapper">
                  <a href={works.link}>
                    <Image src={works.image} />
                    <div className="label text-center">
                      <h3>{works.title}</h3>
                      <p>{works.subtitle}</p>
                    </div>
                  </a>
                </div>
              </Col>
            );
          })}
        </Row>
      </Container>
    </section>
  );
}
