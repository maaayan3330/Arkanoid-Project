Arkanoid Game – Java Implementation
Overview

This project is an object-oriented implementation of the classic Arkanoid arcade game, developed in Java.
The game focuses on clean software design, modular architecture, and accurate real-time game mechanics such as collision detection, score handling, and level progression.

The project was developed as part of a learning process emphasizing OOP principles, event-driven programming, and game loop management.

Game Description

Arkanoid is a brick-breaking game where the player controls a paddle at the bottom of the screen to bounce a ball upward.
The goal is to destroy all bricks on the screen without letting the ball fall below the paddle.

Main mechanics include:

Ball movement and reflection

Paddle control using keyboard input

Brick collision and destruction

Life and score management

Game over and win conditions

Key Features

Object-Oriented Design
Each game component (Ball, Paddle, Brick, GameManager, etc.) is implemented as a separate class with clear responsibilities.

Collision Detection System
Handles interactions between:

Ball and walls

Ball and paddle

Ball and bricks

Game Loop Implementation
Uses a structured update–render loop to ensure smooth gameplay and consistent timing.

User Interaction
Keyboard-based paddle control with responsive movement.

Game State Management
Includes start, running, win, and game-over states.
