<div align="center">
  <h1>Punto Banco</h1>
  <p>Koddy Rae Madriaga & Bryce Carson</p>
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAVrEPeCc0P2OuRUSuguXl3_RIlKh9PvWrIxEnCs0nSjdPw7kq9oM3Len9VYUMtdQOcO4&usqp=CAU">
</div>


# Bryce's contributions
## Beginning of the project
At the beginning of the project I wrote:
- The game logic (hands, gamblers, getting cards, scoring cards)
- The GameManager
- The controller

## Middle
- Fixed the issue with the record not saving properly (it was appending, not overwriting the old file)

Resolved multiple critical gameplay issues due to Gambler.equals(Object o) not being overridden and `==` being used rather than `.equals` in both the controller and JUnit testing. This was a tough one. 🪨
- Fixed the issue with returning players not being created properly
- Fixed the issue with player's who have no money being able to create new player's with the same name

## End
- Wrote lots of JavaDoc and ensured the project had no warnings or errors in either the compilation of the runnable JAR nor in the JavaDoc
- Created the runnable JAR, released it on GitHub, created the README describing our work efforts, and wrote about _my own_ work efforts (Koddy wrote about his own work efforts).

# Koddy's contributions
My main contributions started with working on the methods that deal with the file found in the record class. As I finished the initial version of those methods, I shifted my focus towards the AppMenus and documentation for the project. Documentation included adding comments and documentation headers to all the methods in the projects, and then I worked on testing for the project.

# General summary of work distribution
We each worked on different parts of the project at the beginning, but as the project matured we each modified each other's code as needed, wrote code for each other, and by the end of the project we each understood each other's code and weren't very concerned with who had written what because we knew it all. Wow, that was a long sentence.
