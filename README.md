# HexGrid

Basic explanation of what on earth this is.

The Hex class are immutable 3-dimensional coordinates. These are used to _tag_ resources that are actually useful for us, like the tiles that we'll be using to put minions on.

Because they tag things, they're used as the keys on a hashmap. 

Say we have a minion who needs to move to a tile adjacent. It would figure out what tile it needed to move to, reach into the hashmap, fetch it _using the Hex coordinate_, and move itself to that tile. We'll need to provide wrappers for all these commands, with things like pathfinding (and representing this pathfinding on the screen).
