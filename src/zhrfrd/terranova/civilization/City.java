package zhrfrd.terranova.civilization;

import zhrfrd.terranova.world.World;
import zhrfrd.terranova.world.tile.Tile;

public class City {
	private String name; 
	private int population = 1;
	private int food = 1;
	private int production = 1;
	private int defense = 1;
	Tile tile;
	
	public City(String name, World world) {
		this.name = name;
	}
}
