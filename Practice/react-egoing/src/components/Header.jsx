import React from "react";

export default function Header({ title, onWelcome }) {
  return (
    <header>
      <h1>
        <a href="/" onClick={onWelcome}>
          {title}
        </a>
      </h1>
    </header>
  );
}
