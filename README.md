# Terranova

This is a hobby project about a turn based strategy game called Terranova.
The project wants to be similar to the game [Civilization](https://en.wikipedia.org/wiki/Civilization_(video_game)).

## TODO

- [x] Create the game window.
- [x] Create basic game loop.
- [x] Create tile map.
	- [x] Auto generate a map using Perlin Noise or other algorithms.
	- [x] Been able to identify selected tiles.
	- [ ] When positioning an object on the map, it must be centred in the current tile.
	- [x] Allow movement around the map by dragging and dropping.

## ISSUES

- [ ] Set boundaries when scrolling the map over the edges.
- [ ] Use bitwize operation when possible for optimisation.
- [ ] Improve Perlin Noise map generation. At the moment the map follows some patterns in the generation (i.e. mountains always FULLY surround lakes).