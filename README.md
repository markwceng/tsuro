# Tsuro
Multiplayer implementation of Tsuro using remote clients and a server.

### Example turn
Ansh can view the tiles in his hand to choose from, as well as the one he has currently selected. He can also see the board and the coordinates at which his selected tile will be placed when he confirms his move. The automated referee ensures that only legal placements are allowed.

![Board](tsuro/tsuro.png)

### Subsequent turn
Once Ansh finalizes his move, the board updates in real time to reflect this to all other players. Allen can see that Ansh placed a tile at coordinates (1,0), which caused the player with the white color token to automatically advance further into the board along the newly created path. Allen can now use this information to determine his own move, and the game continues until only one player remains on the board.
![Board](tsuro/tsuro1.png)
