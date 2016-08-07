# HexGrid

Essentially, porting [this] (http://www.redblobgames.com/grids/hexagons/implementation.html#code)(which is in C++) into Java.

Basic explanation of what on earth this is.

The Hex class are immutable 3-dimensional coordinates. These are used to _tag_ resources that are actually useful for us, like the tiles that we'll be using to put minions on.

Because they tag things, they're used as the keys on a hashmap. 

Say we have a minion who needs to move to a tile adjacent. It would figure out what tile it needed to move to, reach into the hashmap, fetch it _using the Hex coordinate_, and move itself to that tile. We'll need to provide wrappers for all these commands, with things like pathfinding (and representing this pathfinding on the screen).

Things TODO:
- Implement the orientation system that converts Hex coordinates to screenspace coordinates (I [Max] can do that)
- Smack on a quick and dirty frontend, so that we can draw the hexagons to the screen and start screwing around with them (preferably not me)
  + Start investigating what the interface would look like if we're going to use JavaFX to render the hexagons
  + What's the system for registering mouse clicks? Do tiles need to know where they are on the screen? Or does FX do that for us?
