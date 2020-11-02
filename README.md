# Tsuro
Multiplayer implementation of Tsuro using remote clients and a server. The traditional rules for Tsuro can be found [here](https://www.ultraboardgames.com/tsuro/game-rules.php) - this implementation differs slightly, but the general concept is still the same.

### Example turn
Ansh can view the tiles in his hand to choose from, as well as the one he has currently selected. He can also see the board and the coordinates at which his selected tile will be placed when he confirms his move. The automated referee ensures that only legal placements are allowed.

![Board](tsuro/tsuro.png)

### Subsequent turn
Once Ansh finalizes his move, the board updates in real time within each player's individual client program to reflect this progression in the game. Allen can now see that Ansh placed a tile at coordinates (1,0), which caused the player with the white color token to automatically advance further into the board along the newly created path. Allen can now use this information to determine his own move, and the game continues this cycle until only one player remains, or a joint victory occurs.

![Board](tsuro/tsuro1.png)
