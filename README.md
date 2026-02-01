# Arkanoid Game – Java Implementation

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange" />
  <img src="https://img.shields.io/badge/OOP-Object--Oriented-blue" />
  <img src="https://img.shields.io/badge/Game%20Type-Arcade-green" />
  <img src="https://img.shields.io/badge/Status-Completed-success" />
</p>

An object-oriented implementation of the classic **Arkanoid** arcade game, developed in Java as part of an academic learning process.

This project emphasizes clean software design, modular architecture, and real-time game mechanics, with a strong focus on OOP principles, event-driven programming, and game loop management.

---

## Game Overview

Arkanoid is a brick-breaking game where the player controls a paddle at the bottom of the screen to bounce a ball upward.  
The objective is to destroy all bricks while preventing the ball from falling below the paddle.

---

## Core Mechanics

- 🏐 Ball movement with realistic reflection
- 🎮 Paddle control via keyboard input
- 🧱 Brick collision and destruction
- ❤️ Life management
- 🏆 Score tracking
- ❌ Game-over and win conditions

---

## Software Design

### Object-Oriented Architecture
Each game component is implemented as a **separate class** with clear responsibilities:

- `Ball`
- `Paddle`
- `Brick`
- `GameManager`
- Additional supporting classes

This separation improves **maintainability**, **readability**, and **extensibility**.

---

## Collision Detection System

- Handles interactions between:
  - Ball and walls
  - Ball and paddle
  - Ball and bricks
- Ensures accurate physics behavior and consistent gameplay

---

## 🔁 Game Loop

- Structured **update–render loop**
- Guarantees smooth animation and consistent timing
- Manages game state updates independently of rendering

---

## 🧭 Game State Management

The game supports multiple states:

- ▶️ Start
- 🟢 Running
- 🏆 Win
- ❌ Game Over

State transitions are handled centrally to ensure clean flow control.

---

## ⌨️ User Interaction

- Keyboard-based paddle control
- Responsive movement for real-time gameplay

---

## 🎓 Learning Outcomes

- Practical application of **Object-Oriented Programming**
- Experience with **event-driven systems**
- Understanding of **game loops and real-time systems**
- Clean architecture and responsibility separation

---

<p align="center">
  Built with ❤️ using Java • Academic Project
</p>

