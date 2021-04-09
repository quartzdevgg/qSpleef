# qSpleef
Spleef plugin - qDev Team

Current Features:
1. Create/Delete spleef arenas
2. Saves arena to file
3. join arena tps to arena, leaving arena tps back
4. can force start arena
5. when arena starts, floor can be broken, 
6. if a player in the arena falls below the floor, state updated to out, and teleported to spectate location
7. when 1 player left, game is finished and last player is broadcasted winner


Todo:
1. Scoreboard and leaderboard (live scoreboard during game, and leaderboard/stats saved in a database)
2. Potentionally track floor break to tell who knocked out who, (idk if its plausible)
3. TabComplete and Aliases
4. Join/Spec locations center on block, (not corner)
5. Maybe add lobby location
6. Joined players can't break ANY blocks, can ONLY break floor blocks IF and ONLY IF game state is running
7. Option to autostart after x amount of players joined (configurable option in config.yml)
8. Add floor have multiple layers, (auto detect lowest y lvl)
9. Hover smart text in chat
10. Add floor-is-lava option (floor below breaks if player stays in same spot too long)
11. Block player from using commands when in an arena
12. Spleef floor different shapes? (idk how to)
13. Rewards
14. min-max players option (configurable in config.yml)
