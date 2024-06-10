# Terranova

This is a hobby project about a turn based strategy game called Terranova.
The project wants to be similar to the browser-game [OGame](https://lobby.ogame.gameforge.com/en_GB/) where you start from a planet and you conquer other planets with a graphic design similar to the old version of [Civilization](https://en.wikipedia.org/wiki/Civilization_(video_game)).
I'm still not sure if developing a space game or a classic land strategy game.

## TODO

- [x] Create the game window.
- [x] Create basic game loop.
- [x] Create tile map.
	- [ ] Add support for selecting a particular tile.
	- [ ] When moving an object on the map, it must be centred in the current tile.
	- [ ] Allow movement around the map by dragging and dropping.
		- [x] Before drag-drop map, support map movement by selecting a particular part of the screen.
	- [ ] Fix IndexOutOfBounds exception when scrolling too south on the map. Need to set boundaries of the map so to limit the scroll 
- [ ] Use bitwize operation when possible for optimisation.