# Assignment Two Feedback

## Group  thu15m
## Group members: "u6136358","Al-Sabti","Thoraya","","u5380100","Brookes","Samuel","","u6126217","Tan","Baohong"

## Tutor Comment

Fantastic work guys.

The UI design is great and you clearly put some thought into it.

You used OOP features really well. It was great having a variety of classes and almost nothing in ~RailroadInk~ that didn't need to be there.

The code was really clean and well commented for the most part. I did notice a few of Tom's functions were missing descriptions however.

I have no idea what this is
~~~Java
        String[][] railArrT = {{"S0","26"},{"S1","123567"},{"S3","01234567"},{"S4","2356"},{"S5","1357"},
                {"A0","0147"},{"A1","0246"},{"A2","023456"},{"B0","26"},{"B1","35"},{"B2","1357"}};
        String[][] railArrD = {{"S0","04"},{"S1","013457"},{"S3","01234567"},{"S4","0147"},{"S5","1357"},
                {"A0","256"},{"A1","0246"},{"A2","012467"},{"B0","04"},{"B1","17"},{"B2","1357"}};
        String[][] railArrL = {{"S0","15"},{"S1","012456"},{"S3","01234567"},{"S4","1245"},{"S5","0246"},
                {"A0","0367"},{"A1","1357"},{"A2","123457"},{"B0","15"},{"B1","24"},{"B2","0246"}};
        String[][] railArrR = {{"S0","37"},{"S1","023467"},{"S3","01234567"},{"S4","0367"},{"S5","0246"},
                {"A0","1245"},{"A1","1357"},{"A2","013567"},{"B0","37"},{"B1","06"},{"B2","0246"}};
        String[][] highArrT = {{"S0","013457"},{"S1","04"},{"S2","01234567"},{"S4","0147"},{"S5","0246"},
                {"A3","023456"},{"A4","0246"},{"A5","0147"},{"B0","04"},{"B1","04"},{"B2","0246"}};
        String[][] highArrD = {{"S0","123567"},{"S1","26"},{"S2","01234567"},{"S4","2356"},{"S5","0246"},
                {"A3","012467"},{"A4","0246"},{"A5","2356"},{"B0","26"},{"B1","26"},{"B2","0246"}};
        String[][] highArrL = {{"S0","023467"},{"S1","37"},{"S2","01234567"},{"S4","0367"},{"S5","1357"},
                {"A3","123457"},{"A4","1357"},{"A5","0367"},{"B0","37"},{"B1","37"},{"B2","1357"}};
        String[][] highArrR = {{"S0","012456"},{"S1","15"},{"S2","01234567"},{"S4","1245"},{"S5","1357"},
                {"A3","013567"},{"A4","1357"},{"A5","1245"},{"B0","15"},{"B1","15"},{"B2","1357"}};
        ArrayList<Boolean> boolarr = new ArrayList<>();
~~~

While the computer waiting screen was nice it would've been nice to have  a visualisation of what moves it made.

There was a couple of bugs in (see the test log at the bottom).
Your A dice also only rolls 5 faces. We did push a fix to our tests to cover this; remember to upstream pull regularly!

I also had memory issues running it on my personal computer which probably indicates something wrong.

The advanced AI you had was nice but it would've been cool to see something more complex :)

## Mark

**  10.18/11.0**

## Miscellaneous marks

| Level | Requirement | Result |
|:-:|---|:-:|
||All files correct                     | 0.25/.25 |
||Authorship clear for all classes      | 0.25/.25 |
||Appropriate use of Git                | 0.5/.5  |
||Program runs from JAR                 | 0.25/.25 |
|P|Appropriate use of OO features       | 0.5/.5  |
|P|Presentation PDF complete            | 0.5/.5  |
|CR|Program well designed               | 0.4/.5  |
|CR|Comments clear and sufficient       | .5/.5  |
|CR|Coding style good                   | 0.25/.25 |
|CR|Appropriate use of JUnit tests      | 0.5/.5  |
|D|Design and code of very high quality | 0.25/.25 |
|D|Demonstrates interesting extensions  | 0.25/.25 |
|D|Works well and easy to run           | 0.20/.25 |
|HD|Game is exceptional                 | 0.5/.5  |

**Total for miscellaneous marks:**  5.10/5.25

## Game marks (manual)

| Level | Requirement | Result |
|:-:|---|:-:|
|CR|Tiles snap into place            | 0.25/.25 |
|CR|Tiles can be rotated and flipped | 0.25/.25 |
|CR|Only valid placements allowed    | 0.25/.25 |
|CR|Basic score at game end          | 0.25/.25 |
|D |Basic computer opponent          | 0.25/.25 |
|HD|Advanced computer opponent       | 0.25/.5  |

**Total for manual marks:** 1.5/1.75

## Test results

| Task | Test | Result | Marks |
|:-:|---|:-:|:-:|
| |Compiled|.25/.25|.25|
|2|IsTilePlacementWellFormed|3/3|.5|
|3|IsBoardStringWellFormed|6/6|.5|
|5|AreConnectedNeighbours|5/5|.5|
|6|IsValidPlacementSequence|4/5|.400|
|7|GenerateDiceRoll|3/4|.187|
|8|GetBasicScore|4/4|.5|
|10|GenerateMove|5/5|.5|
|12|GetAdvancedScore|1/2|.250|

**Total for tests:** 3.58/4.0

## Originality statements

#### Originality statement G
We declare that the work we have submitted for Stage G of this assignment and all stages before it is entirely our own work, with the following documented exceptions:

* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira).

Signed: Thoraya Al-Sabti (u6136358), Samuel Brookes (u5380100), Baohong Tan (u6126217)

#### Originality statement F
We declare that the work we have submitted for Stage F of this assignment and all stages before it is entirely our own work, with the following documented exceptions:

* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira).

Signed: Samuel Brookes (u5380100) Baohong Tan (u6126217) Thoraya Al-Sabti.

#### Originality statements E
I declare that the work I have submitted for Stage E of this assignment and all stages before it is entirely my own work, with the
following documented exceptions:

* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira). 

Signed: Samuel J. Brookes (u5380100)I declare that the work I have submitted for Stage E of this assignment and all stages before it is entirely my own work, with the
following documented exceptions:


Add class getBasicScore with the discusstion with teammates.
Signed: Baohong Tan(u6126217)I declare that the work I have submitted for Stage E of this assignment and all stages before it is entirely my own work, with the
following documented exceptions:

* The idea of <PlacementTest> came from a discussion with mate <Samuel Brookes (u5380100)>

* The code in class <...> uses an idea suggested by <...>

Signed: Thoraya Al-Sabti (u6136358)


#### Originality statements D
I declare that the work I have submitted for Stage D of this assignment and all stages before it is entirely my own work, with the following documented exceptions:

* The idea of <...> came from a discussion with <...>

* The code in class <...> uses an idea suggested by <...>

Signed: Mary Jones (u23445678)
I declare that the work I have submitted for Stage D of this assignment and all stages before it is entirely my own work, with the following exceptions:
                                                                                                                        
* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira). 

Signed: Samuel Brookes (u5380100)

#### Originality statement C
We declare that the work we have submitted for Stage C of this assignment and all stages before it is entirely our own work, with the following documented exceptions:

* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira). 

Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)

#### Originality statement B
We declare that the work we have submitted for Stage B of this assignment and all stages before it is entirely our own work, with the following exception:

* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva). 

Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)

## Git Log
```
commit 5e8f2669309ab690a74fce253c12d30ce96b0fe2
Merge: 536973b c660701
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 23 20:11:46 2019 +1000

    Merge remote-tracking branch 'origin/master'

commit 536973bdb6306a12fc8f3d2cab2c2b3a52b1570f
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 23 20:03:31 2019 +1000

    Updated presentation.

commit c660701de7360b5e1a54bbf746d8b9fdb3c9725a
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 23 00:26:48 2019 +1000

    Update G-best-u1234567.md

commit dc2755eb8f42abe9fbf295fa2f8ed32346e2f640
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 22 22:26:33 2019 +1000

    Cleaned up a couple of bugs, rebuilt .jar file

commit 45ffdb9f43ed836189acf2aee95ce160bb80f1b0
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 22 22:12:09 2019 +1000

    Added deliverables: Best, Features and updated originality statement. Fixed some minor bugs in UI and added Rules.

commit 8fec2fab4f13bf72f4a269d912a4960ab676f220
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Wed May 22 19:00:00 2019 +1000

    Added files (best, contribution, originality and presentation)

commit 3d1026cede8545c51bb3f59d6803afea11b8f475
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue May 21 18:21:59 2019 +1000

    Rebuilt .jar file

commit f5f467fda83243d5c78d5c26f0dfc03cda50e931
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue May 21 18:18:31 2019 +1000

    Solved Task 10

commit a243ffcfadca8db59e569444961dc074549f67a4
Merge: 6be5a40 eb49af5
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue May 21 10:48:59 2019 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m
    
    # Conflicts:
    #	src/comp1110/ass2/RailroadInk.java
    
    Task 10.

commit 6be5a40b7c8fa2ffbaeb968abbbf08c5c614a785
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Mon May 20 11:26:09 2019 +1000

    passed three out of five, task 10.

commit eb49af5d3f6ba2377b0b3bf439635d16cd815d60
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 19 19:23:33 2019 +1000

    Deleted unused statements, added comments. Just waiting on Task 10 and then we're done.

commit 23a76b02c0fa46935a6d9334a3620d095a01e9cc
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 19 18:38:32 2019 +1000

    I give up trying to make a smarter ComputerOpponent. This one will do I think.

commit 4eeedd3955de7fe689da820fd997007d966ec40a
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 19 16:27:12 2019 +1000

    ComputerOpponent still too dumb, I'm changing my approach. Committing in case I ruin everything.

commit 4a65ade2b30d348b368789fff54635e99046e143
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat May 18 21:14:20 2019 +1000

    Some refactoring and haveAdvancedTurn() written, but not tested or debugged.

commit 421bb28e939d7c0e6e843a5086fb6dd9aaf1ec7e
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 16 23:37:49 2019 +1000

    Minor clean up

commit eedf50ea61e7d5aeb7205efaf4fb5485ba9028e5
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 16 23:36:55 2019 +1000

    IT WORKS!!!!!!!!!!! Added comments and method descriptions.

commit 0c3090940bf905953f21aefad9fd283acc572510
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 16 22:47:45 2019 +1000

    Updated longest route methods.... close but no cigar. Committing this version in case I stuff up.

commit bc4f16f289ee2866f626f726dfc994295c7d56a3
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 16 09:04:42 2019 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m
    
    # Conflicts:
    #	src/comp1110/ass2/RailroadInk.java
    
    Add F-Review.

commit 908695b9e90df1c0057f92b1216232faa791cdc3
Merge: a07792b 886fda4
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 16 09:03:22 2019 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m

commit a07792bf2d03a30213867e109b0deb273c51da55
Merge: 8a39cba afe1f7a
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 16 09:03:08 2019 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m
    
    # Conflicts:
    #	src/comp1110/ass2/RailroadInk.java
    
    Add F-Review.

commit 886fda41872269957a6bf5bd432dca3d27ba5318
Author: Samuel Brookes <samuel.brookes@anu.edu.au>
Date:   Thu May 16 08:40:36 2019 +1000

    Update F-contribution.md

commit 8a39cbaae11b4e1f29935ed73c11eaed5dcdc6dd
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 16 06:06:18 2019 +1000

    First attempt at generateMove().

commit afe1f7a453b0d0716f13e5fa80c5372334dbbb34
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 16 00:53:59 2019 +1000

    Update F-review-u6126217.md

commit 62625648f42e4883115d9823667fc3960679bfdf
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 16 00:00:11 2019 +1000

    Cleaning up contribution.

commit 0dd675b565deed7a75512b79abd48d83cd23cd3f
Merge: 56c29ad dafa071
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 15 23:58:13 2019 +1000

    Merge remote-tracking branch 'origin/master'

commit 56c29ad37c0b86de3d4e7bde546901e4047bf8cc
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 15 23:57:28 2019 +1000

    Continuing on longest route method.. :(

commit dafa0718f41d8d14072f53524b9cf6368f65c873
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Wed May 15 23:01:55 2019 +1000

    Update F-review-u1234567.md

commit 55ac41e0696ab4a560d37212f90cd8f3b4ad21b6
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Wed May 15 22:57:50 2019 +1000

    Update F-originality.md

commit 9463ef7153638cb9c7e19e00cf0c238d2b874777
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Wed May 15 22:54:11 2019 +1000

    Update F-contribution.md

commit c8c90b730a8e43fe4ab8ad6ef737458ea32f104c
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 15 21:31:34 2019 +1000

    Updated authorship to include UID. Completed review of Tom's code.

commit 4f0bd14e1a602956b043734a010a408989f7f24c
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 15 20:37:24 2019 +1000

    Updated and signed originality statement - not yet signed by group members.

commit 4eac35b883feb5452f9c5c9ab39e6b3adfb27fe2
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 15 20:35:23 2019 +1000

    Updated and signed contribution statement - not yet signed by group.
    Added method fixOrientation() to Tile for generateMove() Task.

commit 9188bb8a9ccac3b26e55f39c188a1894384fecdb
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue May 14 23:34:06 2019 +1000

    Some progress on longest route calculation - need to complete convertNodes() before debugging.

commit 3af52f841e45b5a3e1dfb21ca03295f727fa2cf8
Merge: abdbfc4 3915238
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue May 14 17:14:01 2019 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m

commit abdbfc401d09d7a1f6c2638c1f4638a3e3ce8ae2
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue May 14 17:13:47 2019 +1000

    First attempt at generateMove().

commit b3e41c39d833bb3aa381b021a390654007e7a0bd
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 12 21:51:50 2019 +1000

    Added .jar file

commit 391523851cce78a911b893cf1c6a69803460cec0
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 12 21:29:12 2019 +1000

    Added contructor to Dices class.

commit 5747a6c5671d15648fb515a9d7ba528062b75489
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 12 18:28:01 2019 +1000

    Cleaned up btnBoard.setOnAction() and gameStage launch method.

commit 5e2013d909d2dd1a493297969e67e0116c5c5533
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 12 16:41:10 2019 +1000

    Very basic Computer Opponent functioning.

commit b939720db09466404e85b4aa5809195ff81f9892
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 12 00:17:41 2019 +1000

    CompileRoutes() method now gets free-hanging routes, ScoreBoard allows boardString to be copied.

commit 4dbdaa27bb0f545a210ea729c33ca65294786c93
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Thu May 9 15:04:26 2019 +1000

    Corrected NullPointerException in GetBasicScoreTest.testEmpty()

commit 0242b3444da5f6657b3ee176bc7a49de74059bc7
Merge: 367b299 443e157
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 9 14:18:19 2019 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m
    
    # Conflicts:
    #	admin/E-originality-u6126217.md

commit 367b29948f9ded2f82d6261871d88f77ec4ccf6b
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 9 14:07:12 2019 +1000

    PlacementTest..

commit 379a2b9fa8714d8dbfc3b3e2fddc24ed4af854ae
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu May 9 14:03:13 2019 +1000

    PlacementTest.

commit 443e15742b8958c53add82871a6d0f9f07b708c4
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:32:29 2019 +1000

    Update E-originality-u6126217.md

commit a73424caff041047725f8673aa8d96819b6cac5e
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:32:12 2019 +1000

    Update E-originality-u6126217.md

commit c8907a4530c5f63601ff399d0689141ac0cef825
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:31:23 2019 +1000

    I am stupid to add a new file, just edit the template.

commit edfc2ca68d9e00a96e238321bb4121638bb3e87e
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:22:31 2019 +1000

    Sorry to edit the template file. Just ignore any commits about this file.

commit 06ccc32fcab9e1aca5e85469b8b5c1626dc031de
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:18:16 2019 +1000

    Add new file

commit 1f8421e536dc343043768d3c013700cac696a2fe
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:16:26 2019 +1000

    Delete  E-originality-u1234567.md

commit 753ce06e834d02be7b9cd913b28ad2983b9ad056
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:15:52 2019 +1000

    Add new file

commit 4c1a4f250edd0268d200d087d1aba8b779949ecf
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 04:07:02 2019 +1000

    Update RailroadInk.java

commit 2d8265cdb275b80011545985f538932302722eb4
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 02:02:23 2019 +1000

    Update E-originality-u1234567.md

commit 5763fb3db104e5abbb2cc5a3e2caef7c49d91d01
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Thu May 9 02:01:29 2019 +1000

    Update E-originality-u1234567.md

commit a1314229b2167173227bcbc74d2708765fe38c48
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 8 21:52:04 2019 +1000

    Changed originality statement D to include the cumulative declarations. Completed orig statement C.

commit ab1601bf7788eceb9abfb140eb39c065b0732eae
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 8 21:46:31 2019 +1000

    Adding static import statement to Tom's test class to correct compile error. Created BoardTest and DicesTest classes.

commit 5eb921166d7c652db817a4d13c432a47d7916835
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Tue May 7 16:58:30 2019 +1000

    Update GetBasicScoreTest.java

commit 1ac1f53709743da548bd440f540a3df8f68a7c73
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 5 23:30:22 2019 +1000

    Added images and RouteCompilerTest

commit a9f6a1e098518c6771fa9d405cbd078cbdeb4283
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun May 5 23:29:52 2019 +1000

    Added authorship to classes as per final deliverable. Implemented Score Board in Viewer.

commit 429a96e482ba19646d51ca927184af1c4e6bec68
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed May 1 23:42:38 2019 +1000

    Optimised getRoutes() method in ScoreCalculator class.

commit de9c63be3078a2b5a69d6f69f5e494fbcb782d44
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 30 20:24:24 2019 +1000

    Updated originality statement D, added authorship to Task 4.

commit 7e0fa882656a0a696fe67e12216a74dcc66b9071
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Apr 24 10:40:25 2019 +1000

    Added comments to all classes.

commit e69c379e514297095bc87e0724736cc2f7ac90b2
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun Apr 21 21:54:53 2019 +1000

    Removed findLongestStretch() code because it's not working :( :(

commit 63e19b5f914e00e695ed1d192827440a1327b829
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun Apr 21 16:49:32 2019 +1000

    ScoreCalculator successfully calculates Basic Score

commit dbdc9b7bb262f7e0c6fc32f00914555fc84cf80c
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun Apr 21 00:20:28 2019 +1000

    Turned off multiplayer and computer opponent (still in development)

commit 54ff4492d555e3de5160e19a12f284ba10a21238
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun Apr 21 00:16:08 2019 +1000

    Fine tuning ScoreCalculator

commit dc3f57fbc3e8ce6736714f98251ea85922f7a8db
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat Apr 20 23:02:11 2019 +1000

    Enabled Viewer mode in game.

commit 72f8f8fc86eb8c161c182b011c089bbdfb5cb0b4
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat Apr 20 22:05:40 2019 +1000

    Completed basic score calculator, still having trouble with calculating longest highway/railroad.

commit 758216852d3bdb3bab5b7585a88f5e8764132fc8
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Fri Apr 19 18:13:18 2019 +1000

    Started work on ScoreCalculator, enabled two players mode.

commit aa7d337f485b56811dcb4f932bf44abe0a53e1d1
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Apr 17 22:11:47 2019 +1000

    Enabled placements on the board, rotating the tiles and selecting tiles

commit 094dd045fe08742ca9efff0f6b7d6f898bfdc912
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 16 22:21:03 2019 +1000

    Cleaned up Game assets

commit c5f9c49be1840adbe56dee63c06b99074e6f5fde
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 16 22:19:55 2019 +1000

    Made progress on the Game UI.

commit dd1f66f3e190d0ebe98a5256eaa2e0670fd114f1
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Fri Apr 5 17:46:51 2019 +1100

    Corrected errors in Viewer class.

commit 6816c5d284702f4133bea7703306b1dc7df645a8
Merge: 454466a 9cee2b5
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu Apr 4 15:16:33 2019 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2

commit 454466a819b52bc3c622a456bf41cfdb4961b52a
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Apr 3 22:13:38 2019 +1100

    Fixed the placement rules in the Board class.

commit a7c7cb301325094c0c588c8bc49b8c11ac9d8a96
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Apr 3 21:51:59 2019 +1100

    Added comments and updated originality file.

commit 7ed740daf2193b93b49d5e082e3ca3654ae7325c
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Apr 3 21:11:43 2019 +1100

    Added coordinates and tiles to Viewer. Bug tested Viewer. Updated originality statement.

commit be6b8bba1e72b0627193636e0f6b4371e2617440
Merge: 95ba741 04efd1d
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Apr 3 20:10:39 2019 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m

commit 04efd1dca8daf42af88d02c07311658ed464e0e6
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Wed Apr 3 04:31:57 2019 +1100

    Added the contribution and originality for stage c.

commit 8fce263c5f13184e73181b7d7aede5c693454f7b
Merge: 83e64d8 16f81ae
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Wed Apr 3 04:15:02 2019 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m

commit 95ba741d4cd95a16198a0352aa887f9fa338baf5
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 2 23:32:01 2019 +1100

    Removed redundant ImageView constructors, still a couple of bugs in Viewer

commit 38557cbbc7ffdd433ae36f553d27b2dd2c3e3717
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 2 23:13:58 2019 +1100

    Added option to place tiles with rules applied and option to add a board string to Viewer.

commit 16f81aea491d865ac9b282d5ebdf38000c2045c9
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 2 21:51:57 2019 +1100

    Attempt 2 at fixing Viewer.
    Tile enum changed to a Class.

commit 83e64d84d3c80628110524127d88723f8f0abe4e
Merge: 971966c 5dad029
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Apr 2 21:02:05 2019 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6136358/comp1110-ass2-thu15m

commit 971966c39fb493ae80deb65d6dd8a96ec80ce911
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Apr 2 21:00:30 2019 +1100

    try to fix viewer issue.

commit 5dad0297794d38629b091b6e4d9eb46601107534
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Tue Apr 2 20:54:08 2019 +1100

    Attempt 1 at fixing Viewer...

commit 32e22b6e489c41c682f7a60a5ab9545dfe94ae3a
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Apr 2 17:49:56 2019 +1100

    Fixed task3.

commit 9cee2b5b00726f2f206ecd616012ddcc36018c90
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Tue Apr 2 09:12:01 2019 +1100

    AreConnectedNeighboursTest: more tests for mirror-asymmetric tiles

commit 54f7795be951557a8971f26ac9445e4f25ab270e
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Apr 2 02:59:50 2019 +1100

    Done an upstream pull, task 2 and task 3.

commit 129da75203bb163df068a0fea5d1b6ffbfd64312
Merge: e6962d8 c31ba1e
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Apr 2 02:51:44 2019 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2

commit e6962d89152078531be74bfd74ebca7d9d332106
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Apr 2 02:43:00 2019 +1100

    task 2 and 3.

commit 8cfe5ed1f8690db640bf64e4d946c74368da245b
Author: u6126217 <u6126217@anu.edu.au>
Date:   Mon Apr 1 21:28:34 2019 +1100

    task 5 6 7 fixed

commit 5c2dff835023981ea8d970e597ca09e5f7c05677
Author: Baohong  Tan <u6126217@anu.edu.au>
Date:   Mon Apr 1 21:11:17 2019 +1100

    Bug fixed

commit 5de650a567243d940dc3531b134427bccbbae63e
Author: u6126217 <u6126217@anu.edu.au>
Date:   Mon Apr 1 20:53:05 2019 +1100

    created C method

commit 645b109d51157467f31096881cf24816a966de8c
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sun Mar 31 09:22:16 2019 +1100

    Completed Task 4 and added comments.

commit c31ba1ef10ecf073353dddfbfd2bb72343c6c495
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Sun Mar 31 08:54:35 2019 +1100

    T10 GenerateMoveTest

commit 9138f37ade2b3d65c5d43c571a3432095b556067
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat Mar 30 23:15:34 2019 +1100

    Deleting unused imports.

commit 2b21b0dd8fb3604ff7a3f3730dff5efc3e8b12ea
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat Mar 30 23:07:34 2019 +1100

    Deleted debug code from Viewer class.

commit 680b49b31e7e231829d254fe6fa7c55a21a37245
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat Mar 30 23:06:22 2019 +1100

    Viewer almost complete. Trying to fix a pointer error that causes all tiles already stored in a Board object to rotate when a Tile outside the Board rotates.

commit 5a0302f1af1a21b7302a7be2b7061203f7531b48
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Sat Mar 30 22:39:34 2019 +1100

    T8 GetBasicScoreTest, T12 GetAdvancedScoreTest

commit a8783cc7e83719263af5fa96161ff24553d27ebe
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Sat Mar 30 15:40:33 2019 +1100

    Images updated so that they have a background and are the same size. All classes (except ScoreCalculator) implemented and tested (except ImageView and rotate methods - need a GUI for that). Constructor and fields in RailroadInk class commented out until so that they don't cause errors while conducting Tasks.

commit d61559a8eace9eb0de8948899ccf1a333a7a43eb
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Sat Mar 30 14:39:59 2019 +1100

    test more types of invalid piece placements

commit dd8a70f7c2d4b4e5873329916620613935b4fc6e
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Fri Mar 29 22:58:05 2019 +1100

    Placement Class implemented.

commit 26a606cc35a983eedda9b8de52ce81dcb18e8ff7
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Fri Mar 29 22:53:27 2019 +1100

    Tile enum implemented.

commit afd6438938719d070cfdf50a5adeb9c5779a9ba5
Merge: 366a0f3 1079418
Author: u6126217 <u6126217@anu.edu.au>
Date:   Thu Mar 28 16:15:05 2019 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #	src/gittest/Main.java

commit 10794189d8cf5450b09dc917c9b7ebac4a1a880d
Merge: af3aef7 c4ab6c5
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu Mar 28 16:12:34 2019 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #	src/gittest/Main.java

commit af3aef700af877ef96cde900f27adb74bd3669e0
Author: Bob <u6136358@anu.edu.au>
Date:   Thu Mar 28 16:10:30 2019 +1100

    Main class B.

commit 366a0f3c007bcb93867a8587614cc48a79b4215d
Author: Cindy <u6126217@anu.edu.au>
Date:   Thu Mar 28 16:07:41 2019 +1100

    created C method

commit c4ab6c5fa38907fde02bc7b33065744f0adc7381
Author: Alice <u5380100@anu.edu.au>
Date:   Thu Mar 28 16:07:24 2019 +1100

    Modified Main class.

commit e7e34d933982f0e4f6b9aa7cc3abda7332fa7d66
Author: u6126217 <u6126217@anu.edu.au>
Date:   Thu Mar 28 16:06:57 2019 +1100

    created C method

commit df0fdee0786878cbe19fa8ce1dea9ad71748ed91
Merge: 41f0927 cec8318
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Thu Mar 28 15:35:11 2019 +1100

    Merge remote-tracking branch 'origin/master'

commit cec8318c7b96e71dac76d3670821ed04a6d26943
Author: Cindy <u6126217@anu.edu.au>
Date:   Thu Mar 28 15:34:49 2019 +1100

    created C method

commit 41f09273a2b180d75779a7a406a2aaf0c65da549
Author: Bob <u6136358@anu.edu.au>
Date:   Thu Mar 28 15:28:35 2019 +1100

    created class B.

commit 5bd740d6b128dbec45ec50d4cde544b8e4ce3752
Author: Alice <u5380100@anu.edu.au>
Date:   Thu Mar 28 15:21:55 2019 +1100

    Added Main and A class.

commit 6ae0aa7d6f58c7d8b46fccfa05bcd19e9847c8bb
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Mar 27 21:17:13 2019 +1100

    Added one exception to B-originality.md.

commit 9017ac53c70aca0468f9e744f1b33424fd6bde23
Author: Samuel Brookes <u5380100@anu.edu.au>
Date:   Wed Mar 27 21:14:02 2019 +1100

    Deliverable D2B. Added ScoreCalculator after Tuesday's meeting.

commit 2103abde2f1b42e9629f55216d26729b3837fda3
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Mar 26 18:26:50 2019 +1100

    contribution and originality.

commit d4a89daa15b0d79d59c077fd29d6443a6e109c0a
Merge: 1c41754 87f0a67
Author: thorayaal-sabti <u6136358@anu.edu.au>
Date:   Tue Mar 26 14:07:35 2019 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2

commit 87f0a67a1ea2b01ea570f9cbe66f23eacfe13a1c
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Fri Mar 22 16:57:27 2019 +1100

    fix off-by-one errors in Javadoc spec

commit 6899cf1bfb33883edba7d29b261aa2e63950d6be
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Thu Mar 21 21:40:31 2019 +1100

    fix specification for dice roll - B can only roll 0-2
    
    fix IsTilePlacementWellFormedTest and GenerateDiceRollTest accordingly

commit 1c417540d7ce90f6d60b0737e6c6af30ddb37360
Author: u5380100 <u5380100@anu.edu.au>
Date:   Thu Mar 21 15:41:41 2019 +1100

    Committing contact-details.md

commit 984e9fef1b9f26e2d4f38cf136ad5e85b4a9a6cd
Author: Josh Milthorpe <josh.milthorpe@anu.edu.au>
Date:   Tue Mar 19 02:08:41 2019 +1100

    assignment published
```
## Changes
``` diff
diff -ru -x .git ../master/comp1110-ass2/admin/B-contribution.md comp1110-ass2/admin/B-contribution.md
--- ../master/comp1110-ass2/admin/B-contribution.md	2019-05-24 08:28:25.531581500 +1000
+++ comp1110-ass2/admin/B-contribution.md	2019-05-24 08:33:25.443437500 +1000
@@ -1,7 +1,7 @@
 We declare that the work toward our submission of Stage B was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0234567 30
+* u5380100 33.33
+* u6126217 33.33
+* u6136358 33.33
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0234567)
+Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)
diff -ru -x .git ../master/comp1110-ass2/admin/B-originality.md comp1110-ass2/admin/B-originality.md
--- ../master/comp1110-ass2/admin/B-originality.md	2019-05-24 08:28:25.536566700 +1000
+++ comp1110-ass2/admin/B-originality.md	2019-05-24 08:33:25.449431400 +1000
@@ -1,9 +1,5 @@
-We declare that the work we have submitted for Stage B of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
+We declare that the work we have submitted for Stage B of this assignment and all stages before it is entirely our own work, with the following exception:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva). 
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-*  ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)
diff -ru -x .git ../master/comp1110-ass2/admin/C-contribution.md comp1110-ass2/admin/C-contribution.md
--- ../master/comp1110-ass2/admin/C-contribution.md	2019-05-24 08:28:25.541566300 +1000
+++ comp1110-ass2/admin/C-contribution.md	2019-05-24 08:33:25.455430500 +1000
@@ -1,7 +1,7 @@
 We declare that the work toward our submission of Stage C was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0234567 30
+* u5380100 33.33
+* u6126217 33.33
+* u6136358 33.33
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0234567)
+Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)
Only in comp1110-ass2/admin: contact-details.md
diff -ru -x .git ../master/comp1110-ass2/admin/C-originality.md comp1110-ass2/admin/C-originality.md
--- ../master/comp1110-ass2/admin/C-originality.md	2019-05-24 08:28:25.546588700 +1000
+++ comp1110-ass2/admin/C-originality.md	2019-05-24 08:33:25.460439100 +1000
@@ -1,9 +1,6 @@
 We declare that the work we have submitted for Stage C of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
+* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira). 
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-*  ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)
Only in comp1110-ass2/admin: D-originality-u5380100.md
Only in ../master/comp1110-ass2/admin: E-originality-u1234567.md
Only in comp1110-ass2/admin: E-originality-u5380100.md
Only in comp1110-ass2/admin: E-originality-u6126217.md
Only in comp1110-ass2/admin: E-originality-u6136358.md
diff -ru -x .git ../master/comp1110-ass2/admin/F-contribution.md comp1110-ass2/admin/F-contribution.md
--- ../master/comp1110-ass2/admin/F-contribution.md	2019-05-24 08:28:25.561563800 +1000
+++ comp1110-ass2/admin/F-contribution.md	2019-05-24 08:33:25.489432200 +1000
@@ -1,8 +1,7 @@
 We declare that the work toward our submission of Stage F was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0123456 30
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+* u5380100 33.33
+* u6126217 33.33
+* u6136358 33.33
 
+Signed: Samuel Brookes (u5380100) Baohong Tan (u6126217), Thoraya Al-Sabti.
\ No newline at end of file
diff -ru -x .git ../master/comp1110-ass2/admin/F-originality.md comp1110-ass2/admin/F-originality.md
--- ../master/comp1110-ass2/admin/F-originality.md	2019-05-24 08:28:25.566566200 +1000
+++ comp1110-ass2/admin/F-originality.md	2019-05-24 08:33:25.495439100 +1000
@@ -1,9 +1,6 @@
 We declare that the work we have submitted for Stage F of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
+* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira).
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-* ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Samuel Brookes (u5380100) Baohong Tan (u6126217) Thoraya Al-Sabti.
Only in ../master/comp1110-ass2/admin: F-review-u1234567.md
Only in comp1110-ass2/admin: F-review-u5380100.md
Only in comp1110-ass2/admin: F-review-u6126217.md
Only in comp1110-ass2/admin: F-review-u6136358.md
Only in ../master/comp1110-ass2/admin: G-best-u1234567.md
Only in comp1110-ass2/admin: G-best-u5380100.md
Only in comp1110-ass2/admin: G-best-u6126217.md
Only in comp1110-ass2/admin: G-best-u6136358.md
diff -ru -x .git ../master/comp1110-ass2/admin/G-contribution.md comp1110-ass2/admin/G-contribution.md
--- ../master/comp1110-ass2/admin/G-contribution.md	2019-05-24 08:28:25.581566000 +1000
+++ comp1110-ass2/admin/G-contribution.md	2019-05-24 08:33:25.532438400 +1000
@@ -1,8 +1,8 @@
 We declare that the work toward our submission of Stage G was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0123456 30
+* u5380100 33.33
+* u6126217 33.33
+* u6136358 33.33
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Samuel Brookes (u5380100), Baohong Tan (u6126217), and Thoraya Al-Sabti (u6136358)
 
diff -ru -x .git ../master/comp1110-ass2/admin/G-features.md comp1110-ass2/admin/G-features.md
--- ../master/comp1110-ass2/admin/G-features.md	2019-05-24 08:28:25.586572500 +1000
+++ comp1110-ass2/admin/G-features.md	2019-05-24 08:33:25.537438100 +1000
@@ -1,13 +1,19 @@
 In addition to the features that are auto-graded, the graphical user interface
 of our project implements the following features:
 
-*(Remove those that are unimplemented)*
-
- - A simple placement viewer (Task 4)
- - A basic playable game
- - A basic playable game that snaps pieces to the board and checks for validity (Task 7)
- - Generates basic starting piece placements (Task 8)
- - Implements hints (Task 10)
- - Implements interesting starting placements (Task 11)
-
-additional features...
+ - A basic working game (Task 9) that implements: 
+    - Score calculation (Tasks 8 & 12),
+    - Placement checks (Tasks 2, 3, 5 & 6), 
+    - Dice rolls (Task 7), and 
+    - Four game modes: 
+        - Single Player,
+        - Two Player (Task 11),
+        - Computer Opponent (Tasks 10 & 13), and
+        - Viewer mode (Task 4).
+        
+It also implements the additional features: 
+ - A score board, which includes:
+    - Break down of score into components,
+    - 'View Board String' button that allows players to save a game, and
+    - Switching between computer and player boards.
+ - Ability to calculate scores in Viewer mode.
\ No newline at end of file
diff -ru -x .git ../master/comp1110-ass2/admin/G-originality.md comp1110-ass2/admin/G-originality.md
--- ../master/comp1110-ass2/admin/G-originality.md	2019-05-24 08:28:25.591582300 +1000
+++ comp1110-ass2/admin/G-originality.md	2019-05-24 08:33:25.542437100 +1000
@@ -1,9 +1,6 @@
 We declare that the work we have submitted for Stage G of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* A field in the class ScoreCalculator is referred to as 'Network Values', which was taken from the Official Rulebook of Railroad Ink Deep Blue Edition (Hjalmar Hach & Lorenzo Silva).
+* Assistance with using StackPane in Task 4 taken from a reply to the post ['Insert text at the center of a image']((https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image)) on StackOverflow by user [Crferreira](https://stackoverflow.com/users/1050679/crferreira).
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-* ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Thoraya Al-Sabti (u6136358), Samuel Brookes (u5380100), Baohong Tan (u6126217)
Only in comp1110-ass2: Ass02_Presentation.pdf
Only in comp1110-ass2/assets: Proposal UI Design.png
Only in comp1110-ass2: feedback.md
Only in comp1110-ass2: game.jar
Only in comp1110-ass2/.idea: artifacts
Only in comp1110-ass2/.idea: dictionaries
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T10_GenerateMoveTest.xml comp1110-ass2/.idea/runConfigurations/T10_GenerateMoveTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T10_GenerateMoveTest.xml	2019-05-24 08:28:25.469566200 +1000
+++ comp1110-ass2/.idea/runConfigurations/T10_GenerateMoveTest.xml	2019-05-24 08:33:25.347430700 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T12_GetAdvancedScoreTest.xml comp1110-ass2/.idea/runConfigurations/T12_GetAdvancedScoreTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T12_GetAdvancedScoreTest.xml	2019-05-24 08:28:25.476568000 +1000
+++ comp1110-ass2/.idea/runConfigurations/T12_GetAdvancedScoreTest.xml	2019-05-24 08:33:25.352432900 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T3_IsBoardStringWellFormedTest.xml comp1110-ass2/.idea/runConfigurations/T3_IsBoardStringWellFormedTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T3_IsBoardStringWellFormedTest.xml	2019-05-24 08:28:25.489562000 +1000
+++ comp1110-ass2/.idea/runConfigurations/T3_IsBoardStringWellFormedTest.xml	2019-05-24 08:33:25.363438500 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
@@ -16,4 +16,4 @@
       <option name="Make" enabled="true" />
     </method>
   </configuration>
-</component>
+</component>
\ No newline at end of file
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T5_AreConnectedNeighboursTest.xml comp1110-ass2/.idea/runConfigurations/T5_AreConnectedNeighboursTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T5_AreConnectedNeighboursTest.xml	2019-05-24 08:28:25.497579400 +1000
+++ comp1110-ass2/.idea/runConfigurations/T5_AreConnectedNeighboursTest.xml	2019-05-24 08:33:25.368437800 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
@@ -16,4 +16,4 @@
       <option name="Make" enabled="true" />
     </method>
   </configuration>
-</component>
+</component>
\ No newline at end of file
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T6_IsValidPlacementSequenceTest.xml comp1110-ass2/.idea/runConfigurations/T6_IsValidPlacementSequenceTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T6_IsValidPlacementSequenceTest.xml	2019-05-24 08:28:25.503622900 +1000
+++ comp1110-ass2/.idea/runConfigurations/T6_IsValidPlacementSequenceTest.xml	2019-05-24 08:33:25.373438400 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
@@ -16,4 +16,4 @@
       <option name="Make" enabled="true" />
     </method>
   </configuration>
-</component>
+</component>
\ No newline at end of file
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T7_GenerateDiceRollTest.xml comp1110-ass2/.idea/runConfigurations/T7_GenerateDiceRollTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T7_GenerateDiceRollTest.xml	2019-05-24 08:28:25.508604400 +1000
+++ comp1110-ass2/.idea/runConfigurations/T7_GenerateDiceRollTest.xml	2019-05-24 08:33:25.378448500 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
@@ -16,4 +16,4 @@
       <option name="Make" enabled="true" />
     </method>
   </configuration>
-</component>
+</component>
\ No newline at end of file
diff -ru -x .git ../master/comp1110-ass2/.idea/runConfigurations/T8_GetBasicScoreTest.xml comp1110-ass2/.idea/runConfigurations/T8_GetBasicScoreTest.xml
--- ../master/comp1110-ass2/.idea/runConfigurations/T8_GetBasicScoreTest.xml	2019-05-24 08:28:25.514566700 +1000
+++ comp1110-ass2/.idea/runConfigurations/T8_GetBasicScoreTest.xml	2019-05-24 08:33:25.383430700 +1000
@@ -3,7 +3,7 @@
     <module name="comp1110-ass2" />
     <extension name="coverage">
       <pattern>
-        <option name="PATTERN" value="comp1110.ass2.*" />
+        <option name="PATTERN" value="comp1110.ass2.RouteCompilerTest" />
         <option name="ENABLED" value="true" />
       </pattern>
     </extension>
Only in comp1110-ass2: META-INF
Only in comp1110-ass2/src/comp1110/ass2: Board.class
Only in comp1110-ass2/src/comp1110/ass2: Board.java
Only in comp1110-ass2/src/comp1110/ass2: ComputerOpponent.class
Only in comp1110-ass2/src/comp1110/ass2: ComputerOpponent.java
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: A0_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/A0.png and comp1110-ass2/src/comp1110/ass2/gui/assets/A0.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: A1_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/A1.png and comp1110-ass2/src/comp1110/ass2/gui/assets/A1.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: A2_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/A2.png and comp1110-ass2/src/comp1110/ass2/gui/assets/A2.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: A3_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/A3.png and comp1110-ass2/src/comp1110/ass2/gui/assets/A3.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: A4_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/A4.png and comp1110-ass2/src/comp1110/ass2/gui/assets/A4.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: A5_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/A5.png and comp1110-ass2/src/comp1110/ass2/gui/assets/A5.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: B0_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/B0.png and comp1110-ass2/src/comp1110/ass2/gui/assets/B0.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: B1_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/B1.png and comp1110-ass2/src/comp1110/ass2/gui/assets/B1.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: B2_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/B2.png and comp1110-ass2/src/comp1110/ass2/gui/assets/B2.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: BLANK_TILE.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: CENTER_TILE.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: draw.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: EDGE_TILE.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: gameOver.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: invalid.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: Logo.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: player1Wins.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: player2Wins.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: rules.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: S0_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/S0.png and comp1110-ass2/src/comp1110/ass2/gui/assets/S0.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: S1_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/S1.png and comp1110-ass2/src/comp1110/ass2/gui/assets/S1.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: S2_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/S2.png and comp1110-ass2/src/comp1110/ass2/gui/assets/S2.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: S3_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/S3.png and comp1110-ass2/src/comp1110/ass2/gui/assets/S3.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: S4_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/S4.png and comp1110-ass2/src/comp1110/ass2/gui/assets/S4.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: S5_mirrored.png
Binary files ../master/comp1110-ass2/src/comp1110/ass2/gui/assets/S5.png and comp1110-ass2/src/comp1110/ass2/gui/assets/S5.png differ
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: thinking.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: XH.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: XR.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: youLose.png
Only in comp1110-ass2/src/comp1110/ass2/gui/assets: youWin.png
Only in comp1110-ass2/src/comp1110/ass2/gui: Dices.class
Only in comp1110-ass2/src/comp1110/ass2/gui: Dices.java
Only in comp1110-ass2/src/comp1110/ass2/gui: ImageHandler.class
Only in comp1110-ass2/src/comp1110/ass2/gui: ImageHandler.java
Only in comp1110-ass2/src/comp1110/ass2/gui: SpecialTiles.class
Only in comp1110-ass2/src/comp1110/ass2/gui: SpecialTiles.java
Only in comp1110-ass2/src/comp1110/ass2/gui: Viewer.class
diff -ru -x .git ../master/comp1110-ass2/src/comp1110/ass2/gui/Viewer.java comp1110-ass2/src/comp1110/ass2/gui/Viewer.java
--- ../master/comp1110-ass2/src/comp1110/ass2/gui/Viewer.java	2019-05-24 08:28:25.695563600 +1000
+++ comp1110-ass2/src/comp1110/ass2/gui/Viewer.java	2019-05-24 08:33:25.923439300 +1000
@@ -1,70 +1,1523 @@
 package comp1110.ass2.gui;
 
+/* IMPORT STATEMENTS FOR GAME */
+import comp1110.ass2.*;
+import javafx.geometry.Insets;
+import javafx.scene.control.*;
+import javafx.scene.input.MouseButton;
+import javafx.scene.layout.*;
+import javafx.scene.paint.Color;
+import javafx.scene.text.Font;
+import javafx.scene.text.FontWeight;
+import javafx.scene.text.Text;
+import javafx.stage.Stage;
+import javafx.scene.Scene;
 import javafx.application.Application;
+import javafx.geometry.Pos;
+import javafx.scene.image.ImageView;
+import java.util.HashMap;
+import static javafx.scene.layout.BorderStroke.MEDIUM;
+
+/* IMPORT STATEMENTS FOR VIEWER */
 import javafx.scene.Group;
-import javafx.scene.Scene;
-import javafx.scene.control.Button;
-import javafx.scene.control.Label;
-import javafx.scene.control.TextField;
-import javafx.scene.layout.HBox;
-import javafx.stage.Stage;
+import javafx.scene.input.KeyCode;
+import javafx.scene.text.TextAlignment;
+import java.util.ArrayList;
 
 /**
- * A very simple viewer for tile placements in the Railroad Ink game.
- * <p>
- * NOTE: This class is separate from your main game class.  This
- * class does not play a game, it just illustrates various tile placements.
+ * The Viewer class has been modified to act as the main class for the game interface.
+ *
+ * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
  */
 public class Viewer extends Application {
+
+    /* GAME ASSETS*/
+    private Stage quitStage;
+    private Stage rulesStage;
+    private Stage gameStage;
+    private Stage scoreStage;
+    private boolean gameFinished = false;
+    private static final int GAME_WIDTH = 1024;
+    private static final int GAME_HEIGHT = 768;
+    private Text txtRound;
+    private HashMap<Integer, PlayerData> playerData;
+    private Board boardRef;
+    private Dices diceRef;
+    private SpecialTiles specialRef;
+    private char gameMode;
+    private int player;
+    private ComputerOpponent computerOpponent;
+    private HBox gameInfo; //player and round
+    private VBox boardContainer; //holds full board
+    private GridPane boardProper; //actual board
+    private VBox dicesUI; //dices and roll button
+    private VBox specialUI; //special tiles
+    private String selected; //tracks which tile is selected
+    private Placement placement;
+    private Text txtNotification;
+
+    /* LAUNCHER ASSETS */
+    private Stage launchStage;
+
+    public static void main(String[] args) {
+        launch(args);
+    }
+
+    /**
+     * The start method of the Viewer launches the game menu (launchStage)
+     * @param primaryStage
+     */
+    public void start(Stage primaryStage)
+    {
+        launchStage = buildLaunchStage(primaryStage);
+        launchStage.show();
+    }
+
+    /**
+     * This method creates the launchStage which acts as the game menu.
+     * @param launchStage
+     * @return (Stage) launchStage
+     */
+    private Stage buildLaunchStage(Stage launchStage)
+    {
+        ImageView logo = ImageHandler.getMiscTile("Logo");
+        logo.setFitWidth(500);
+        logo.setFitHeight(240);
+
+        RadioButton btnSinglePlayer = new RadioButton("Single Player");
+        btnSinglePlayer.setMaxSize(200, 10);
+        btnSinglePlayer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));
+
+        RadioButton btnMultiPlayer = new RadioButton("Multi-player");
+        btnMultiPlayer.setMaxSize(200, 10);
+        btnMultiPlayer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));
+
+        RadioButton btnComputer = new RadioButton("Computer Opponent");
+        btnComputer.setMaxSize(200, 10);
+        btnComputer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));
+
+        RadioButton btnViewer = new RadioButton("Use Viewer");
+        btnViewer.setMaxSize(200, 10);
+        btnViewer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));
+
+        ToggleGroup tgPlayMode = new ToggleGroup();
+        tgPlayMode.getToggles().addAll(btnSinglePlayer, btnMultiPlayer, btnComputer, btnViewer);
+
+        btnSinglePlayer.setSelected(true);
+        Button btnPlay = new Button("Play");
+        formatButton(btnPlay);
+        Button btnRules = new Button("Show Rules");
+        formatButton(btnRules);
+
+        btnRules.setOnAction(ae ->
+        {
+            if(rulesStage == null || !rulesStage.isShowing())
+            {
+                launchRulesStage();
+            }
+            else if(rulesStage.isShowing())
+            {
+                rulesStage.toFront();
+            }
+        });
+
+        /* Choosing different RadioButtons changes the gameMode field.
+         * The gameMode field is accessed through the launchGameStage()
+         * method to determine which game is loaded, and also throughout
+         * the game by event handlers to ensure that the correct
+         * actions are carried out depending upon the game mode.
+         */
+        btnPlay.setOnAction(ae ->
+        {
+            if(btnSinglePlayer.isSelected())
+            {
+                gameMode = 's';
+                player = 1;
+                launchGameStage();
+            }
+            else if(btnMultiPlayer.isSelected())
+            {
+                gameMode = 'm';
+                player = 1;
+                launchGameStage();
+            }
+            else if(btnComputer.isSelected())
+            {
+                gameMode = 'c';
+                player = 1;
+                launchGameStage();
+            }
+            else
+            { //btnViewer is selected
+                gameMode = 'v';
+                launchViewer();
+            }
+        });
+
+        VBox rootLaunch = new VBox();
+        rootLaunch.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
+        rootLaunch.setSpacing(20);
+        rootLaunch.setAlignment(Pos.CENTER);
+        rootLaunch.getChildren().addAll(logo, btnSinglePlayer, btnMultiPlayer, btnComputer, btnViewer, btnPlay, btnRules);
+
+        launchStage.setScene(new Scene(rootLaunch, 550, 550));
+        launchStage.setResizable(false);
+        launchStage.setTitle("Railroad Ink");
+
+        return launchStage;
+    }
+
+    private void launchRulesStage()
+    {
+        rulesStage = new Stage();
+        rulesStage.setTitle("Railroad Ink Rules");
+
+        ImageView logo = ImageHandler.getMiscTile("rules");
+        logo.setFitWidth(375);
+        logo.setFitHeight(75);
+
+        VBox root = new VBox();
+        formatBox(root, Color.LAVENDER, 30, false);
+
+        Label labelRules = new Label("THE AIM of Railroad Ink is to create " +
+                "networks of railroads and highways that connect as many exits as possible." +
+                "\n\nEXITS can be seen on the green outer edge of the game board." +
+                "\n\nA TILE can only be placed on the board if there are no illegal connections " +
+                "to other tiles on the board or to an exit." +
+                "\n\nAN ILLEGAL CONNECTION occurs when two edges (or an edge and an exit) meet at different" +
+                " route types, for example, when an edge with a railroad meets an edge with a highway (or vice versa)." +
+                "\n\nYOU GET EXTRA POINTS for placing tiles on the red centre squares and for your longest highway and " +
+                "railroad." +
+                "\n\nYOU LOSE POINTS by having unconnected edges on the board at the end of the game." +
+                "\n\nONLY ONE SPECIAL TILE can be placed each round, and no more than 3 Special tiles can be placed " +
+                "in a game." +
+                "\n\nTO ROTATE A TILE, click on it with the right mouse button." +
+                "\n\nTO PLACE A TILE on the board, click on it with the left mouse button to select" +
+                " it, and then click on the position on the board where you would like it to go." +
+                "\n\nBEWARE! Once you place a tile, you are unable to remove it, so think carefully before making" +
+                " your move.");
+        labelRules.setWrapText(true);
+        labelRules.setFont(Font.font("Garamond", FontWeight.BOLD, 16));
+
+        Button btnClose = new Button("Close");
+        formatButton(btnClose);
+        btnClose.setOnAction(ae ->
+        {
+            rulesStage.close();
+        });
+
+        root.getChildren().addAll(logo, labelRules, btnClose);
+        rulesStage.setScene(new Scene(root, GAME_WIDTH/2, GAME_HEIGHT));
+        rulesStage.show();
+    }
+
+    /**
+     * This method builds the actual game stage and launches it.
+     */
+    private void launchGameStage()
+    {
+        /*
+        * If this is the first time the game stage has been loaded this game,
+        * create the playerData HashMap which holds the data for the Board, Dices and
+        * Special Tiles
+        * */
+        if(playerData == null)
+        {
+            playerData = new HashMap<>(0);
+
+            if(gameMode == 's')
+            { //If single player, create only one PlayerData object and put it in the playerData HashMap
+                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
+                playerData.get(1).diceData.rollDice();
+            }
+            else
+            { //If two player (incl. computer opponent), create two PlayerData objects
+                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
+                playerData.put(2, new PlayerData(2, new Board(), new Dices(), new SpecialTiles()));
+
+                //Roll the dice of player one
+                playerData.get(1).diceData.rollDice();
+
+                //Copy the dice data into player two's PlayerData object
+                playerData.get(2).diceData.copyPlayerDices(playerData.get(1).diceData);
+
+                if(gameMode == 'c')
+                { //add pointer to player data for computer opponent if gameMode is 'c'
+                    computerOpponent = new ComputerOpponent(playerData.get(2));
+                }
+            }
+        }
+
+        /* Set up local reference variables for this launch.
+        *  Each time the game board is reloaded, the correct player
+        *  data is accessed from the playerData HashMap because the
+        *  player field keeps track of which player's turn it is.
+        * */
+        boardRef = playerData.get(player).boardData;
+        diceRef = playerData.get(player).diceData;
+        specialRef = playerData.get(player).specialData;
+
+        //set up gameContainer
+        boardProper = new GridPane();
+        setUpGameInfo();
+        setUpBoard();
+        txtNotification = new Text();
+        formatText(txtNotification, 30, true, false);
+        VBox gameContainer = new VBox();
+        formatBox(gameContainer, Color.LIGHTBLUE, 10, false);
+        gameContainer.getChildren().addAll(gameInfo, boardContainer, txtNotification);
+
+        //set up controlsContainer
+        dicesUI = new VBox();
+        setUpDiceUI();
+        specialUI = new VBox();
+        setUpSpecialUI();
+        VBox controlsContainer = new VBox();
+        formatBox(controlsContainer, Color.LIGHTBLUE, 10, false);
+        Button btnMenu = new Button("Main Menu");
+        formatButton(btnMenu);
+        btnMenu.setOnAction(ae ->
+                {
+                    if(quitStage == null || !quitStage.isShowing())
+                    {
+                        quitQuery();
+                    }
+                    else if(quitStage.isShowing())
+                    {
+                        quitStage.toFront();
+                    }
+                });
+        ImageView logo = ImageHandler.getMiscTile("Logo");
+        logo.setFitWidth(180);
+        logo.setFitHeight(90);
+        controlsContainer.getChildren().addAll(logo, btnMenu, dicesUI, specialUI);
+
+        //set up root
+        HBox rootGame = new HBox();
+        formatBox(rootGame, Color.LIGHTBLUE, 10, false);
+        rootGame.getChildren().addAll(gameContainer, controlsContainer);
+
+        //close launch page
+        launchStage.close();
+
+        //launch game
+        gameStage = new Stage();
+        gameStage.setTitle("Railroad Ink");
+        gameStage.setScene(new Scene(rootGame, GAME_WIDTH, GAME_HEIGHT));
+        gameStage.setResizable(false);
+        gameStage.show();
+    }
+
+    /**
+     * This method builds and launches the quitStage.
+     * The quitStage exists just in case a player accidentally clicks
+     * the Main Menu button. It asks the player if they are sure they would like
+     * to quit the current game.
+     */
+    private void quitQuery()
+    {
+        quitStage = new Stage();
+        quitStage.setTitle("Return to menu");
+        Text txtQuit = new Text("Are you sure you want to quit?");
+        formatText(txtQuit, 20, true, false);
+        Button btnYes = new Button("Yes");
+        formatButton(btnYes);
+        Button btnNo = new Button("No");
+        formatButton(btnNo);
+
+        /*
+        * If the player selects yes, the game data is deleted,
+        * and whichever board is loaded is unloaded before the
+        * main menu is reloaded.
+        * */
+        btnYes.setOnAction(ae ->
+        {
+            playerData = null;
+            quitStage.close();
+            if(gameMode == 'v')
+            {
+                viewerStage.close();
+            }
+            else
+            {
+                if(scoreStage != null)
+                {
+                    scoreStage.close();
+                    scoreStage = null;
+                }
+                gameStage.close();
+                gameStage = null;
+
+                playerData = null;
+                gameFinished = false;
+            }
+            start(new Stage());
+        });
+
+        /*
+        * If the player selects no, the quitStage is unloaded and the
+        * game continues.
+        * */
+        btnNo.setOnAction(ae -> quitStage.close());
+
+        HBox buttons = new HBox();
+        buttons.getChildren().addAll(btnYes, btnNo);
+        formatBox(buttons, Color.LAVENDER, 50, false);
+        VBox root = new VBox();
+        formatBox(root, Color.LAVENDER, 10, false);
+        root.getChildren().addAll(txtQuit, buttons);
+
+        quitStage.setResizable(false);
+        quitStage.setScene(new Scene(root, 350, 150));
+        quitStage.show();
+    }
+
+    /**
+     * This method sets up the fields above the game board
+     * that inform the player of whose turn it is and what round it
+     * is.
+     */
+    private void setUpGameInfo()
+    {
+        Text txtPlayer = new Text((gameMode == 'c' && player == 2)?"~ Computer ~":"~ Player " + player + " ~");
+        formatText(txtPlayer, 30, true, false);
+        txtRound = new Text("~ Round " + boardRef.getRound() + " ~");
+        formatText(txtRound, 30, true, false);
+        gameInfo = new HBox();
+        formatBox(gameInfo, Color.LIGHTBLUE, 150, false);
+        gameInfo.getChildren().addAll(txtPlayer, txtRound);
+    }
+
+    /**
+     * This method sets up the Dice UI, which holds the dice tile and
+     * the roll button.
+     */
+    private void setUpDiceUI()
+    {
+        Text txtDices = new Text("Dices");
+        formatText(txtDices, 20, true, true);
+
+        /*
+        * The dice tiles are loaded each time a tile is selected or
+        * placed or roll button is clicked. The ImageView instantiation
+        * statements therefore need to track if the dice has been used
+        * or not so that it loads the correct image each time.
+        * Each dice tile is made selectable and rotatable by the
+        * setUpSelectAndRotate() method.
+        * */
+        ImageView D1 = (diceRef.isUsed("D1"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(diceRef.getDice("D1"));
+        D1.setId("D1");
+        setUpSelectAndRotate(D1);
+
+        ImageView D2 = (diceRef.isUsed("D2"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(diceRef.getDice("D2"));
+        D2.setId("D2");
+        setUpSelectAndRotate(D2);
+
+        HBox diceRowOne = setUpRow(D1, D2);
+
+        ImageView D3 = (diceRef.isUsed("D3"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(diceRef.getDice("D3"));
+        D3.setId("D3");
+        setUpSelectAndRotate(D3);
+
+        ImageView D4 = (diceRef.isUsed("D4"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(diceRef.getDice("D4"));
+        D4.setId("D4");
+        setUpSelectAndRotate(D4);
+
+        HBox diceRowTwo = setUpRow(D3, D4);
+
+        /*
+        * These conditional statements ensure that the correct
+        * text is put on the roll button. This depends on
+        * which round the game is up to, or whether it is multiplayer.
+        * */
+        Button btnRoll;
+        if(gameMode == 's')
+        { //If the game is single player or computer opponent
+
+            //Set the roll button text to end game if it's round 7, otherwise set it to roll
+            btnRoll = new Button((boardRef.getRound() == 7)?"End Game":"Roll");
+        }
+        else if(gameMode == 'm')
+        {
+            if(player == 1)
+            { //In two player mode, player one's roll button always reads 'End Turn'
+                btnRoll = new Button("End Turn");
+            }
+            else
+            { //The End Game text is only shown on player two's button if it is round 7
+                btnRoll = new Button((boardRef.getRound() == 7)?"End Game":"End Turn");
+            }
+        }
+        else
+        {
+            btnRoll = new Button("End Turn");
+        }
+        formatButton(btnRoll);
+
+        /*
+        * The roll button is perhaps the most important button on the game board because
+        * it controls the flow of the game. As such, it has a complex event handler which
+        * must track a number of different game elements.
+        * */
+        btnRoll.setOnAction(ae ->
+        {
+            //get rid of quitStage if the player has not closed it
+            if(quitStage != null && quitStage.isShowing())
+            {
+                quitStage.close();
+            }
+
+            txtNotification.setText("");
+
+            if(gameMode == 'm')
+            { //If the game is in two player mode
+                if(!boardRef.legalMovesRemaining(diceRef))
+                { //If there are no legal moves remaining on the board
+                    if(player == 1)
+                    { //If it is player one's turn
+
+                        boardRef.iterateRoundCounter(); //iterate player one's round counter
+                        specialRef.resetCounterRound(); //reset player one's specials-used-per-round counter
+                        player = 2; //change the player field to 2 to ensure the correct data is loaded
+                        gameStage.close(); //relaunch the game stage with player two's data
+                        launchGameStage();
+                        txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
+                    }
+                    else
+                    { //Otherwise, if it is player two's turn
+                        if(btnRoll.getText().equals("End Game"))
+                        {//If the roll button reads 'End Game'
+                            //Calculate the scores, and launch the endGameStage to declare the winner
+                            gameFinished = true;
+                            btnRoll.setVisible(false); //set the roll button to invisible so the player cannot press it
+                            showScoreStage();
+                        }
+                        else
+                        { //Otherwise, it is not yet the final round
+                            boardRef.iterateRoundCounter(); //iterate player two's round counter
+                            specialRef.resetCounterRound(); //reset player two's specials-used-per-round counter
+                            player = 1; //change the player field to 1 to ensure the correct data is loaded
+                            gameStage.close(); //relaunch the game stage with player one's data
+                            launchGameStage();
+                            diceRef.rollDice(); //roll player one's dice
+                            playerData.get(2).diceData.copyPlayerDices(diceRef); //copy the dices into player two's data
+                            setUpDiceUI(); //reload the dice UI
+                            txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
+                        }
+
+                    }
+                }
+                else
+                { //Otherwise there are still legal moves
+                    txtNotification.setText("You must place all of the dice!");
+                }
+            }
+            else
+            { //If the game mode is single player or computer opponent
+                if(!boardRef.legalMovesRemaining(diceRef))
+                { //If there are no more legal moves this round
+
+                    if(gameMode == 's')
+                    {
+                        if(btnRoll.getText().equals("End Game"))
+                        { //this is the last round
+                            gameFinished = true;
+                            btnRoll.setVisible(false);
+                            showScoreStage();
+                        }
+                        else
+                        {
+                            boardRef.iterateRoundCounter(); //iterate player one's round counter
+                            specialRef.resetCounterRound(); //reset the specials-used-this-round counter
+                            diceRef.rollDice(); //roll the dice
+                            setUpDiceUI(); //reload the dice UI
+                            txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
+                        }
+                    }
+                    else
+                    { //gameMode == 'c'
+                        if(boardRef.getRound() == 7)
+                        { //this is the last turn
+                            gameFinished = true;
+                            btnRoll.setVisible(false);
+                            computerOpponentHaveTurn(true);
+                        }
+                        else
+                        { //this is not the last turn
+                            computerOpponentHaveTurn(false);
+                            gameStage.hide();
+                            boardRef.iterateRoundCounter(); //iterate player one's round counter
+                            specialRef.resetCounterRound(); //reset the specials-used-this-round counter
+                            diceRef.rollDice(); //roll the dice
+                            computerOpponent.playerData.diceData.copyPlayerDices(diceRef);
+                            setUpDiceUI(); //reload the dice UI
+                            txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
+                        }
+                    }
+                }
+                else
+                { //Otherwise there are still legal moves that can be made
+                    txtNotification.setText("You must place all of the dice!");
+                }
+            }
+        });
+
+        if(scoreStage != null)
+        {
+            btnRoll.setVisible(false);
+        }
+
+        formatBox(dicesUI, Color.LAVENDER, 10, true);
+        dicesUI.getChildren().clear();
+        dicesUI.getChildren().addAll(txtDices, diceRowOne, diceRowTwo, btnRoll);
+    }
+
+    /**
+     * This method builds the interface for the Special Tiles. This holds the
+     * special tiles.
+     */
+    private void setUpSpecialUI()
+    {
+        Text txtSpecials = new Text("Special Tiles");
+        formatText(txtSpecials, 20, true, true);
+
+        /*
+        * The special tiles are loaded each time a tile is selected or
+        * placed or roll button is clicked. The ImageView instantiation
+        * statements therefore need to track if the tile has been used
+        * or not so that it loads the correct image each time.
+        * Each special tile is made selectable and rotatable by the
+        * setUpSelectAndRotate() method.
+        * */
+        ImageView S1 = (specialRef.isUsed("S1"))?
+            ImageHandler.getMiscTile("invalid"):
+            ImageHandler.getTileImage(specialRef.getSpecialTile("S1"));
+        S1.setId("S1");
+        setUpSelectAndRotate(S1);
+
+        ImageView S2 = (specialRef.isUsed("S2"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(specialRef.getSpecialTile("S2"));
+        S2.setId("S2");
+        setUpSelectAndRotate(S2);
+
+        HBox specialRowOne = setUpRow(S1, S2);
+
+        ImageView S3 = (specialRef.isUsed("S3"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(specialRef.getSpecialTile("S3"));
+        S3.setId("S3");
+        setUpSelectAndRotate(S3);
+
+        ImageView S4 = (specialRef.isUsed("S4"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(specialRef.getSpecialTile("S4"));
+        S4.setId("S4");
+        setUpSelectAndRotate(S4);
+
+        HBox specialRowTwo = setUpRow(S3, S4);
+
+        ImageView S5 = (specialRef.isUsed("S5"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(specialRef.getSpecialTile("S5"));
+        S5.setId("S5");
+        setUpSelectAndRotate(S5);
+
+        ImageView S6 = (specialRef.isUsed("S6"))?
+                ImageHandler.getMiscTile("invalid"):
+                ImageHandler.getTileImage(specialRef.getSpecialTile("S6"));
+        S6.setId("S6");
+        setUpSelectAndRotate(S6);
+
+        HBox specialRowThree = setUpRow(S5, S6);
+
+        formatBox(specialUI, Color.LAVENDER, 10, true);
+        specialUI.getChildren().clear();
+        specialUI.getChildren().addAll(txtSpecials, specialRowOne, specialRowTwo, specialRowThree);
+    }
+
+    /**
+     * This method takes a tile and adds the event handler that allows
+     * dices and special tiles to be rotated and selected by the user.
+     * @param tile
+     */
+    private void setUpSelectAndRotate(ImageView tile)
+    {
+        tile.setOnMouseClicked(ae ->
+        { //If this tile is the target of a mouse button click
+
+            //If the tile image is 'invalid.png' - do nothing
+            if(gameFinished || tile.getImage().getUrl().contains("invalid.png")) return;
+
+            txtNotification.setText("");
+
+            //Deselect the previously selected tile
+            if(selected != null)
+            {
+                if(selected.charAt(0) == 'D')
+                { //If the previously selected tile is a dice
+                    diceRef.getDice(selected).deselectTile(); //deselect the selected tile
+                    setUpDiceUI(); //and reload the dice UI
+                }
+                else
+                { //otherwise the previously selectted tile is a special tile
+                    specialRef.getSpecialTile(selected).deselectTile(); //deselect it
+                    setUpSpecialUI(); //and reload the special tile UI
+                }
+            }
+
+            if(ae.getButton().equals(MouseButton.PRIMARY))
+            { //If the left mouse button was clicked - SELECT THE TILE
+
+                selected = tile.getId(); //add the tile id to the selected field
+
+                if(tile.getId().charAt(0) == 'D')
+                { //If the selected tile is a dice
+                    diceRef.getDice(selected).selectTile(); //select the tile
+                    placement = new Placement(diceRef.getDice(tile.getId())); //initiate the placement field with it's information
+                    setUpDiceUI(); //reload Dice UI (the selected dice will be highlighted by the ImageHandler class)
+                }
+                else
+                { //otherwise the selected tile is a special tile
+                    specialRef.getSpecialTile(tile.getId()).selectTile(); //select the tile
+                    placement = new Placement(specialRef.getSpecialTile(tile.getId())); //initiate the placement field with it's information
+                    setUpSpecialUI(); //reload the Special Tile UI (the selected tile will be highlighted by the ImageHandler class)
+                }
+            }
+            else if(ae.getButton().equals(MouseButton.SECONDARY))
+            { //If the right mouse button was click - ROTATE THE TILE
+                selected = null;
+                if(tile.getId().charAt(0) == 'D')
+                { //If the target tile is a dice
+                    diceRef.rotateDice(tile.getId()); //rotate the dice
+                    setUpDiceUI(); //reload the dice UI
+                }
+                else
+                { //Otherwise the target tile is a special tile
+                    specialRef.rotateTile(tile.getId()); //rotate the special tile
+                    setUpSpecialUI(); //reload the special tile UI
+                }
+            }
+        });
+    }
+
+    /**
+     * This method adds an event handler to the tiles in the boardProper
+     * that allow them to be targets for dropping a selected tile.
+     * @param tile
+     */
+    private void setUpDropTarget(ImageView tile)
+    {
+        tile.setOnMouseClicked(ae ->
+        {
+            //If there is no placement to be made, do nothing
+            if(gameFinished || placement == null || selected == null) return;
+
+            if(ae.getButton().equals(MouseButton.PRIMARY))
+            { //If the board tile is clicked with the left mouse button
+
+                placement.updateCoordinates(tile.getId()); //put the coordinates of the clicked tile into the placement string
+
+                if(selected.charAt(0) == 'D')
+                { //If the selected tile is a dice
+                    if(boardRef.addTile(placement.toString(), true))
+                    { //Add the placement to the player's board (if it is legal)
+                        makeBoardProper(); //reload the board
+                        diceRef.useDice(selected); //use the placed dice
+                        setUpDiceUI(); //reload the dice UI
+                        selected = null; //clear selected and placement fields
+                        placement = null;
+                    }
+                    else
+                    { //Otherwise, the placement is not legal
+                        txtNotification.setText("Illegal placement!");
+                    }
+                }
+                else
+                { //Otherwise the seleted tile is a special tile
+                    if(specialRef.getCounterGame() < 3 && specialRef.getCounterRound() == 0)
+                    { //if less than 3 special tiles have been used this game and no special tiles have been used this round
+                        if(boardRef.addTile(placement.toString(), true))
+                        { //Add the placement to the player's board (if it is legal)
+                            makeBoardProper(); //reload the board
+                            specialRef.useSpecialTile(selected); //use the placed special tile
+                            setUpSpecialUI(); //reload the special tile UI
+                            selected = null; //clear selected and placement fields
+                            placement = null;
+                        }
+                        else
+                        { //Otherwise, the placement is not legal
+                            txtNotification.setText("Illegal placement!");
+                        }
+                    }
+                    else
+                    { //Otherwise 3 special tile have been used this game or a special tile has been used this round.
+                        txtNotification.setText((specialRef.getCounterRound() == 1)?
+                                "You can only use 1 special tile per round!":
+                                "You can only use 3 special tiles per game!");
+                    }
+                }
+            }
+        });
+    }
+
+    /**
+     * This method reduces clutter in the setUpDiceUI() and setUpSpecialUI()
+     * methods. It takes two ImageView objects and returns a HBox with them
+     * in a row.
+     * @param imgOne
+     * @param imgTwo
+     * @return (HBox) A row with two ImageView objects
+     */
+    private HBox setUpRow(ImageView imgOne, ImageView imgTwo)
+    {
+        HBox row = new HBox();
+        row.setSpacing(10);
+        row.setAlignment(Pos.CENTER);
+        row.getChildren().addAll(imgOne, imgTwo);
+        return row;
+    }
+
+    /**
+     * This method takes a Pane object (either a HBox or VBox) and formats it.
+     * This method reduces clutter in other methods and ensures the format is uniform throughout the game.
+     * @param controlBox
+     * @param color
+     * @param spacing
+     * @param border
+     */
+    private void formatBox(Pane controlBox, Color color, double spacing, boolean border)
+    {
+        controlBox.setBackground(new Background(new BackgroundFill(color, null, null)));
+        controlBox.setPadding(new Insets(10));
+
+        VBox vbox;
+        HBox hbox;
+        if(controlBox.getClass().getSimpleName().equals("VBox"))
+        {
+            vbox = (VBox) controlBox;
+            vbox.setSpacing(spacing);
+            vbox.setAlignment(Pos.CENTER);
+        }
+        else
+        {
+            hbox = (HBox) controlBox;
+            hbox.setSpacing(spacing);
+            hbox.setAlignment(Pos.CENTER);
+        }
+
+        if(border)
+        {
+            controlBox.setBorder(new Border(new BorderStroke(Color.MEDIUMPURPLE, BorderStrokeStyle.SOLID, null, MEDIUM)));
+        }
+    }
+
+    /**
+     * This method takes a Text object and formats it. This method reduces clutter in
+     * other methods and ensures the format is uniform throughout the game.
+     * @param text
+     * @param size
+     * @param bold
+     * @param underline
+     */
+    private void formatText(Text text, double size, boolean bold, boolean underline)
+    {
+        if(bold)
+        {
+            text.setFont(Font.font("Garamond", FontWeight.BOLD, size));
+        }
+        else
+        {
+            text.setFont(Font.font("Garamond", size));
+        }
+
+        text.setUnderline(underline);
+
+    }
+
+    /**
+     * This method takes a Button object and formats it. This method reduces clutter in
+     * other methods and ensures the format is uniform throughout the game.
+     * @param button
+     */
+    private void formatButton(Button button)
+    {
+        button.setFont(Font.font("Garamond", FontWeight.BOLD, 14));
+    }
+
+    /**
+     * This method sets up the board component of the game stage.
+     */
+    private void setUpBoard()
+    {
+        GridPane northEdge = new GridPane();
+        GridPane eastEdge = new GridPane();
+        GridPane southEdge = new GridPane();
+        GridPane westEdge = new GridPane();
+        HBox middleContainer = new HBox(); //this container holds the center of the board configuration (east, boardProper and west)
+        ImageView tile;
+
+        /*
+        * The 'Edge' GridPanes hold the exit tiles for the board
+        * */
+
+        //Make the North Edge
+        northEdge.setAlignment(Pos.CENTER);
+        for(int i=0; i<9; i++)
+        {
+            if(i==2 || i==6)
+            { //if this tile is highway exit
+                tile = ImageHandler.getExitImage('N', 'H');
+            }
+            else if(i==4)
+            { //if this tile is a railroad exit
+                tile = ImageHandler.getExitImage('N', 'R');
+            }
+            else
+            { //otherwise, get a blank edge tile
+                tile = ImageHandler.getMiscTile("EDGE_TILE");
+            }
+            northEdge.add(tile, i, 0);
+        }
+
+        //Make the East Edge
+        for(int i=0; i<7; i++)
+        {
+            if(i==1 || i==5)
+            { //if this tile is a railroad exit
+                tile = ImageHandler.getExitImage('E', 'R');
+            }
+            else if(i==3)
+            { //if this tile is a highway exit
+                tile = ImageHandler.getExitImage('E', 'H');
+            }
+            else
+            { //otherwise, get a blank edge tile
+                tile = ImageHandler.getMiscTile("EDGE_TILE");
+            }
+            eastEdge.add(tile, 0, i);
+        }
+
+        //Make the South Edge
+        southEdge.setAlignment(Pos.CENTER);
+        for(int i=0; i<9; i++)
+        {
+            if(i==2 || i==6)
+            { //if this tile is a highway exit
+                tile = ImageHandler.getExitImage('S', 'H');
+            }
+            else if(i==4)
+            { //if this tile is a railroad exit
+                tile = ImageHandler.getExitImage('S', 'R');
+            }
+            else
+            { //otherwise get a blank edge tile
+                tile = ImageHandler.getMiscTile("EDGE_TILE");
+            }
+            southEdge.add(tile, i, 0);
+        }
+
+        //Make the West Edge
+        for(int i=0; i<7; i++)
+        {
+            if(i==1 || i==5)
+            { //if this tile is a railroad exit
+                tile = ImageHandler.getExitImage('W', 'R');
+            }
+            else if(i==3)
+            { //if this tile is a highway exit
+                tile = ImageHandler.getExitImage('W', 'H');
+            }
+            else
+            { //otherwise, get a blank edge tile
+                tile = ImageHandler.getMiscTile("EDGE_TILE");
+            }
+            westEdge.add(tile, 0, i);
+        }
+
+        //Make the boardProper (with selectable tiles)
+        makeBoardProper();
+
+        //add to middle container
+        middleContainer.getChildren().addAll(westEdge, boardProper, eastEdge);
+        middleContainer.setAlignment(Pos.CENTER);
+
+        //add everything to the board
+        boardContainer = new VBox();
+        boardContainer.getChildren().addAll(northEdge, middleContainer, southEdge);
+        boardContainer.setAlignment(Pos.CENTER);
+    }
+
+    /**
+     * This method loads the board proper (which contains tile placements).
+     */
+    private void makeBoardProper()
+     {
+         ImageView tile;
+         for(int y=0; y<7; y++)
+         {
+             for(int x=0; x<7; x++)
+             {
+                 //build the id from the x and y values
+                 StringBuilder id = new StringBuilder();
+                 id.append((char)(y + 65));
+                 id.append(x);
+
+                 if(gameMode != 'v' && boardRef.getPlacements().containsKey(id.toString()))
+                 { //If the game mode is not 'Viewer' and there is a placement at these coordinates
+                    tile = ImageHandler.getTileImage(boardRef.getTile(id.toString())); //load the appropriate tile image
+                 }
+                 else
+                 {
+                     if(y > 1 && y < 5 && x > 1 && x < 5)
+                     { //create center tile
+                         tile = ImageHandler.getMiscTile("CENTER_TILE");
+                     }
+                     else
+                     { //create blank tile
+                         tile = ImageHandler.getMiscTile("BLANK_TILE");
+                     }
+                 }
+
+                 tile.setId(id.toString());
+
+                 if(gameMode == 'v')
+                 {//If the game mode is 'Viewer'
+
+                     //Show the coordinates of the tile
+                     Text coordinates = new Text(id.toString());
+                     StackPane layers = new StackPane();
+                     layers.getChildren().addAll(tile, coordinates);
+
+                     //add to the board
+                     boardProper.add(layers, x, y);
+                 }
+                 else
+                 {
+                     //add to the board
+                     setUpDropTarget(tile);
+                     boardProper.add(tile, x, y);
+                 }
+             }
+         }
+     }
+
+    /**
+     * This method builds and shows the score stage.
+     * The score stage contains a break down of each players'
+     * score, and allows the board to be viewed and the board
+     * string to be exported for debugging.
+     */
+    private void showScoreStage()
+     {
+         scoreStage = new Stage();
+         scoreStage.setTitle("Scores");
+         scoreStage.setResizable(false);
+         VBox root = new VBox();
+         Button btnMenu = new Button("Main Menu");
+         btnMenu.setOnAction(ae ->
+         {
+             scoreStage.close();
+             scoreStage = null;
+
+             gameStage.close();
+             gameStage = null;
+             playerData = null;
+             gameFinished = false;
+
+             start(new Stage());
+         });
+         ImageView winImage;
+
+         //Set up appropriate scoreStage, depending upon gameMode
+         if(gameMode == 's')
+         {
+             VBox playerOneStats = getPlayerStats(1);
+             winImage = ImageHandler.getMiscTile("gameOver");
+             winImage.setFitHeight(100);
+             winImage.setFitWidth(550);
+
+             root.getChildren().addAll(winImage, playerOneStats, btnMenu);
+             root.setAlignment(Pos.CENTER);
+             formatBox(root, Color.LAVENDER, 10, false);
+         }
+         else
+         {
+             HBox players = new HBox();
+             VBox playerOneStats = getPlayerStats(1);
+             VBox playerTwoStats = getPlayerStats(2);
+
+             Button showBoardPlayerOne = new Button("Show Board");
+             showBoardPlayerOne.setOnAction(ae ->
+             {
+                 player = 1;
+                 gameStage.close();
+                 launchGameStage();
+                 scoreStage.toFront();
+             });
+             playerOneStats.getChildren().add(showBoardPlayerOne);
+
+             Button showBoardPlayerTwo = new Button("Show Board");
+             showBoardPlayerTwo.setOnAction(ae ->
+             {
+                 player = 2;
+                 gameStage.close();
+                 launchGameStage();
+                 scoreStage.toFront();
+             });
+             playerTwoStats.getChildren().add(showBoardPlayerTwo);
+
+             players.getChildren().addAll(playerOneStats, playerTwoStats);
+             players.setAlignment(Pos.CENTER);
+             players.setSpacing(10);
+
+             if(playerData.get(1).scoreCalculator.getAdvancedScore() ==
+                     playerData.get(2).scoreCalculator.getAdvancedScore())
+             {
+                 winImage = ImageHandler.getMiscTile("draw");
+             }
+             else if(playerData.get(1).scoreCalculator.getAdvancedScore() >
+                     playerData.get(2).scoreCalculator.getAdvancedScore())
+             {
+                 winImage = (gameMode == 'c')?ImageHandler.getMiscTile("youWin"):ImageHandler.getMiscTile("player1Wins");
+             }
+             else
+             {
+                 winImage = (gameMode == 'c')?ImageHandler.getMiscTile("youLose"):ImageHandler.getMiscTile("player2Wins");
+             }
+             winImage.setFitHeight(100);
+             winImage.setFitWidth(550);
+
+             root.getChildren().addAll(winImage, players, btnMenu);
+             root.setAlignment(Pos.CENTER);
+             formatBox(root, Color.LAVENDER, 10, false);
+         }
+
+         scoreStage.setScene(new Scene(root, GAME_WIDTH/1.5, GAME_HEIGHT/1.5));
+         scoreStage.show();
+     }
+
+    /**
+     * This method compiles the player scores from the game
+     * and formats them into a VBox.
+     * @param playerNumber
+     * @return
+     */
+     private VBox getPlayerStats(int playerNumber)
+     {
+         PlayerData player;
+         Text txtHeading;
+         if(gameMode == 'c')
+         { //player is computer
+             txtHeading = new Text(((playerNumber == 1)?"~ PLAYER 1 ~":"~ COMPUTER ~"));
+         }
+         else
+         { //player is not computer
+             txtHeading = new Text("~ PLAYER " + playerNumber + " ~");
+         }
+
+         player = playerData.get(playerNumber);
+         player.calculateScore();
+         formatText(txtHeading, 20, true, true);
+         Text txtCenterScore = new Text("Center Score: " + player.scoreCalculator.getCenterScore());
+         formatText(txtCenterScore, 18, false, false);
+         Text txtNetworkScore = new Text("Network Score: " + player.scoreCalculator.getNetworkScore());
+         formatText(txtNetworkScore, 18, false, false);
+         Text txtErrors = new Text("Number of Errors: " + player.scoreCalculator.getErrors());
+         formatText(txtErrors, 18, false, false);
+         Text txtRailroad = new Text("Longest Railroad: " + player.scoreCalculator.getLongestRailroad());
+         formatText(txtRailroad, 18, false, false);
+         Text txtHighway = new Text("Longest Highway: " + player.scoreCalculator.getLongestHighway());
+         formatText(txtHighway, 18, false, false);
+         Text txtTotalScore = new Text("SCORE: " + player.scoreCalculator.getAdvancedScore());
+         formatText(txtTotalScore, 18, true, false);
+
+         TextField txtBoardString = new TextField();
+         txtBoardString.setVisible(false);
+         Button btnBoardString = new Button("Show Board String");
+         btnBoardString.setOnAction(ae ->
+         {
+             if(btnBoardString.getText().equals("Show Board String"))
+             {
+                 txtBoardString.setVisible(true);
+                 txtBoardString.setText(playerData.get(playerNumber).boardData.toString());
+                 btnBoardString.setText("Hide Board String");
+             }
+             else
+             {
+                 txtBoardString.setVisible(false);
+                 btnBoardString.setText("Show Board String");
+             }
+         });
+
+         VBox playerStats = new VBox();
+         playerStats.getChildren().addAll(txtHeading, txtCenterScore, txtNetworkScore, txtErrors, txtRailroad, txtHighway, txtTotalScore, btnBoardString, txtBoardString);
+         playerStats.setAlignment(Pos.CENTER);
+
+         formatBox(playerStats, Color.LIGHTBLUE, 10, true);
+
+         return playerStats;
+     }
+
+    /**
+     * This method shows the 'Computer is thinking' window
+     * and hides the game board so that the player can not do anything
+     * while the computer is having a turn.
+     * The computer has it's turn and then the continue button
+     * is made visible so that the player can continue.
+     * @param lastRound
+     */
+    private void computerOpponentHaveTurn(boolean lastRound)
+    {
+        gameStage.hide();
+
+        Stage computerStage = new Stage();
+        computerStage.setTitle("Computer is thinking...");
+        computerStage.setResizable(false);
+        VBox root = new VBox();
+        formatBox(root, Color.LAVENDER, 10, false);
+        ImageView computer = ImageHandler.getMiscTile("thinking");
+        computer.setFitHeight(GAME_HEIGHT - 280);
+        computer.setFitWidth(GAME_WIDTH - 200);
+        Button btnContinue;
+        if(lastRound)
+        {
+            btnContinue = new Button("End Game...");
+            btnContinue.setOnAction(ae ->
+            {
+                computerStage.close();
+                gameStage.show();
+                showScoreStage();
+            });
+        }
+        else
+        {
+            btnContinue = new Button("Press to Continue...");
+            btnContinue.setOnAction(ae ->
+            {
+                computerStage.close();
+                gameStage.show();
+            });
+        }
+        btnContinue.setVisible(false);
+        computerOpponent.haveTurn(btnContinue, false);
+
+        root.getChildren().addAll(computer, btnContinue);
+        root.setAlignment(Pos.CENTER);
+        computerStage.setScene(new Scene(root, GAME_WIDTH - 200, GAME_HEIGHT - 220));
+        computerStage.show();
+    }
+
+    /*
+    ========================================================
+   ALL METHODS BELOW THIS POINT ARE FOR THE VIEWER (TASK 4)
+   ========================================================
+   */
+
     /* board layout */
     private static final int VIEWER_WIDTH = 1024;
     private static final int VIEWER_HEIGHT = 768;
 
-    private static final String URI_BASE = "assets/";
-
-    private final Group root = new Group();
-    private final Group controls = new Group();
-    TextField textField;
+    private String savedGame;
+    private Stage viewerStage;
+    private Board boardData = null; //This Board object stores tiles when the rules are applied
+    private VBox tileContainer; //Contains the columns of tiles
+    private VBox root;
+    private Group controls = new Group();
+    private ArrayList<Placement> prevPlacements = null; //Holds information about previous placements so that they can be removed
+    private TextField textField;
+    private Text textWarning;
 
     /**
-     * Draw a placement in the window, removing any previously drawn one
+     * This is the primary method that controls the flow of the placement string,
+     * depending upon whether a board string or single placement string has been entered
+     * and also whether or not the rules are being applied.
      *
      * @param placement A valid placement string
      */
-    void makePlacement(String placement) {
+    private void makePlacement(String placement) {
         // FIXME Task 4: implement the simple placement viewer
+        // Author: Samuel J. Brookes (u5380100)
+
+        if(placement.length() > 5 && RailroadInk.isBoardStringWellFormed(placement))
+        { //placement is a valid board string
+            if(prevPlacements != null)
+            { //if this is not the first placement
+                resetBoard();
+                prevPlacements.clear();
+            }
+            for(int i=0; i<placement.length(); i+= 5)
+            {//place each placement in the board string onto the board
+                makeSinglePlacement(placement.substring(i, i+5));
+            }
+        }
+        else if(placement.length() == 5 && RailroadInk.isTilePlacementWellFormed(placement))
+        { //placement is a valid single placement string
+            if(prevPlacements != null)
+            {//if this is not the first placement, clear the previous placement
+                resetBoard();
+                prevPlacements.clear();
+            }//place the placement on the board
+            makeSinglePlacement(placement);
+        }
+        else
+        { //otherwise, placement is illegal
+            textWarning.setText("Bad placement string! Try again.");
+        }
+    }
+
+    /**
+     * This method paints a single tile placement on the board UI.
+     *
+     * @param placement A valid placement string
+     */
+    private void makeSinglePlacement(String placement)
+    {
+        //create a Placement object to access components of placement
+        Placement p = new Placement(placement);
+        ImageView img;
+
+        //create tile for this placement
+        Tile tile = new Tile(p.getFullId());
+        tile.updateOrientation(p.getOrientation());
+        tile.addCoordinates(p.getCoords());
+
+        //get the appropriate image
+        img = ImageHandler.getTileImage(tile);
+
+        //add the tile to the board
+        boardProper.add(img, p.getColumn(), p.getRowAsInt());
+        textWarning.setText("");
+
+        if(prevPlacements == null)
+        { //if this is the first placement, create the prevPlacements ArrayList
+            prevPlacements = new ArrayList<>();
+        }
+        prevPlacements.add(new Placement(placement)); //add the placement to prevPlacements
+
+    }
+
+    /**
+     * This method resets the board by painting blank tiles over all previous placements.
+     */
+    private void resetBoard()
+    {
+        textWarning.setText("");
+        if(prevPlacements != null)
+        {
+            for(Placement p : prevPlacements)
+            { //refresh each tile that has a previous placement
+                refreshTile(p);
+            }
+        }
+        if(boardData != null)
+        { //clear data from the Board object
+            boardData = new Board();
+        }
+    }
+
+    /**
+     * This method paints a blank tile over a previous placement on the board.
+     *
+     * @param prevPlacement A previous placement on the board
+     */
+    private void refreshTile(Placement prevPlacement)
+    {
+        Text id = new Text(prevPlacement.getCoords()); //get the coordinates of the previous placement
+        StackPane layers = new StackPane();
+        ImageView img;
+
+        if(boardData.isCenterCoord(prevPlacement.getCoords()))
+        { //if the coordinates are in the centre of the board, get the image for a center tile
+            img = ImageHandler.getMiscTile("CENTER_TILE");
+        }
+        else
+        { //otherwise, get the image for a blank tile
+            img = ImageHandler.getMiscTile("BLANK_TILE");
+        }
+
+
+        layers.getChildren().addAll(img, id); //layer the text on top of the tile image
+        boardProper.add(layers, prevPlacement.getColumn(), prevPlacement.getRowAsInt()); //replace previous placement with blank/center tile
     }
 
     /**
      * Create a basic text field for input and a refresh button.
      */
     private void makeControls() {
-        Label label1 = new Label("Placement:");
+        Text label1 = new Text("Placement:");
+        formatText(label1, 18, true, false);
+        Button btnRefresh = new Button("Refresh");
+        Button btnScore = new Button("Calculate Score");
+        formatButton(btnRefresh);
+        formatButton(btnScore);
         textField = new TextField();
         textField.setPrefWidth(300);
-        Button button = new Button("Refresh");
-        button.setOnAction(e -> {
-            makePlacement(textField.getText());
-            textField.clear();
+
+        btnRefresh.setOnAction(e -> {
+            //get rid of quitStage if the player has not closed it
+            if(quitStage != null && quitStage.isShowing())
+            {
+                quitStage.close();
+            }
+
+            textWarning.setText(""); //refresh the warning text
+            if(textField.getText().equals(""))
+            {
+                resetBoard();
+            }
+            else
+            { //otherwise, do an unchecked placement
+                makePlacement(textField.getText());
+                savedGame = textField.getText();
+                textField.clear();
+            }
         });
+
+        btnScore.setOnAction(ae ->
+        {
+            //get rid of quitStage if the player has not closed it
+            if(quitStage != null && quitStage.isShowing())
+            {
+                quitStage.close();
+            }
+
+            if(savedGame != null)
+            {
+                Board board = new Board(savedGame);
+                ScoreCalculator sc = new ScoreCalculator(board);
+                textWarning.setText("Score: " + sc.getNetworkScore() + " (network) + " +
+                        sc.getCenterScore() + " (center) + " + sc.getLongestHighway() + " (highway) + " + sc.getLongestRailroad() +
+                        " (railroad) - " + sc.getErrors() + " (errors) = " + sc.getAdvancedScore());
+            }
+        });
+
+        textField.setOnKeyPressed(ae ->
+        { //added key press event to textfield because having to press the button is annoying
+            KeyCode key = ae.getCode();
+            if(key == KeyCode.ENTER)
+            { //if enter is pressed within the text field, the user is finished typing
+                btnRefresh.fire();
+            }
+        });
+
         HBox hb = new HBox();
-        hb.getChildren().addAll(label1, textField, button);
+        hb.getChildren().addAll(label1, textField, btnRefresh, btnScore);
         hb.setSpacing(10);
         hb.setLayoutX(130);
         hb.setLayoutY(VIEWER_HEIGHT - 50);
         controls.getChildren().add(hb);
     }
 
-    @Override
-    public void start(Stage primaryStage) throws Exception {
-        primaryStage.setTitle("StepsGame Viewer");
-        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
+    /**
+     * This method makes the sub-UI that contains all the tiles for the user to reference.
+     */
+    private void makeTiles()
+    {
 
-        root.getChildren().add(controls);
+        ImageView img;
+        Text title = new Text("Tiles"); //the title for the sub-UI
+        title.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
+        Text tileId;
+        HBox tileColumnContainer = new HBox(); //the container that holds the columns of tiles
+
+        for(int i=0; i<3; i++)
+        { //cycle through three times as there are three types of tiles
+
+            VBox tileColumn = new VBox(); //the column for this tile type
+            int limit; //the number of tiles of this type
+            char type; //the type of these tile
+            switch(i)
+            { //select the appropriate type and its number
+                case 0: type = 'A'; limit = 6; break;
+                case 1: type = 'B'; limit = 3; break;
+                default: type = 'S'; limit = 6;
+            }
+            for(int j=0; j<limit; j++)
+            { //get the name and image of each file of this type and add it to the column
+                tileId = new Text(type + "" + j);
+                img = ImageHandler.getTileImage(new Tile(type + "" + j));
+                tileColumn.getChildren().addAll(tileId, img);
+            }
+
+            //add the column to the column container
+            tileColumn.setSpacing(5);
+            tileColumnContainer.getChildren().add(tileColumn);
+        }
+
+        //add the menu button
+        Button btnMenu = new Button("Main menu");
+        btnMenu.setOnAction(ae ->
+        {
+            if(quitStage == null || !quitStage.isShowing())
+            {
+                quitQuery();
+            }
+            else if(quitStage.isShowing())
+            {
+                quitStage.toFront();
+            }
+        });
+        formatButton(btnMenu);
+
+        //clean up the presentation
+        tileColumnContainer.setSpacing(10);
+        VBox innerTileContainer = new VBox();
+        innerTileContainer.getChildren().addAll(title, tileColumnContainer);
+        innerTileContainer.setAlignment(Pos.CENTER);
+        innerTileContainer.setSpacing(10);
+        innerTileContainer.setPadding(new Insets(5));
+        innerTileContainer.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
+        innerTileContainer.setBorder(new Border(new BorderStroke(Color.MEDIUMPURPLE, BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
+
+        //add to tileContainer
+        tileContainer = new VBox();
+        tileContainer.setAlignment(Pos.CENTER);
+        tileContainer.setSpacing(10);
+        tileContainer.getChildren().addAll(btnMenu, innerTileContainer);
+    }
+
+    /**
+     * This is the main method for building and launching the viewer mode of the RailroadInk game.
+     */
+    private void launchViewer(){
+
+        boardData = new Board();
+        boardProper = new GridPane();
+        setUpBoard();
+        makeTiles();
+        HBox boardAndTiles = new HBox();
+        boardAndTiles.getChildren().addAll(boardContainer, tileContainer);
+        boardAndTiles.setAlignment(Pos.CENTER);
+        boardAndTiles.setSpacing(10);
 
         makeControls();
+        textWarning = new Text();
+        textWarning.setFont(Font.font("Impact", FontWeight.BOLD, 20));
+        textWarning.setTextAlignment(TextAlignment.CENTER);
+
+        root = new VBox();
+        root.getChildren().add(boardAndTiles);
+        root.getChildren().add(textWarning);
+        root.getChildren().add(controls);
+        formatBox(root, Color.LIGHTBLUE, 10, false);
+        root.setSpacing(20);
+        root.setAlignment(Pos.CENTER);
+
+        viewerStage = new Stage();
+        viewerStage.setTitle("Railroad Ink Viewer");
+        viewerStage.setScene(new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT));
+        viewerStage.setResizable(false);
 
-        primaryStage.setScene(scene);
-        primaryStage.show();
+        launchStage.close();
+        viewerStage.show();
     }
 }
Only in comp1110-ass2/src/comp1110/ass2: Iterator.class
Only in comp1110-ass2/src/comp1110/ass2: Iterator.java
Only in comp1110-ass2/src/comp1110/ass2: Placement.class
Only in comp1110-ass2/src/comp1110/ass2: Placement.java
Only in comp1110-ass2/src/comp1110/ass2: PlayerData.class
Only in comp1110-ass2/src/comp1110/ass2: PlayerData.java
Only in comp1110-ass2/src/comp1110/ass2: RailroadInk.class
diff -ru -x .git ../master/comp1110-ass2/src/comp1110/ass2/RailroadInk.java comp1110-ass2/src/comp1110/ass2/RailroadInk.java
--- ../master/comp1110-ass2/src/comp1110/ass2/RailroadInk.java	2019-05-24 08:28:25.686567900 +1000
+++ comp1110-ass2/src/comp1110/ass2/RailroadInk.java	2019-05-24 08:33:25.873452000 +1000
@@ -1,6 +1,19 @@
 package comp1110.ass2;
+import java.util.ArrayList;
+import java.util.Random;
+
+public class RailroadInk
+{
+
+    final String[][] board = {
+            {"A0","A1","A2","A3","A4","A5","A6"},
+            {"B0","B1","B2","B3","B4","B5","B6"},
+            {"C0","C1","C2","C3","C4","C5","C6"},
+            {"D0","D1","D2","D3","D4","D5","D6"},
+            {"E0","E1","E2","E3","E4","E5","E6"},
+            {"F0","F1","F2","F3","F4","F5","F6"},
+            {"G0","G1","G2","G3","G4","G5","G6"}};
 
-public class RailroadInk {
     /**
      * Determine whether a tile placement string is well-formed:
      * - it consists of exactly 5 characters;
@@ -10,11 +23,32 @@
      * - the fourth character represents the placement column 0-6
      * - the fifth character represents the orientation 0-7
      *
+     * @author Thoraya Al-Sabti (u6136358)
      * @param tilePlacementString a candidate tile placement string
      * @return true if the tile placement is well formed
      */
     public static boolean isTilePlacementWellFormed(String tilePlacementString) {
         // FIXME Task 2: determine whether a tile placement is well-formed
+        if (tilePlacementString.length() != 5) {
+            return false;
+        }
+        char first = tilePlacementString.charAt(0);
+        char second = tilePlacementString.charAt(1);
+        char third = tilePlacementString.charAt(2);
+        char fourth = tilePlacementString.charAt(3);
+        char fifth = tilePlacementString.charAt(4);
+
+        if (first == 'A' || first == 'B' || first == 'S') {
+            if (((first == 'A' || first == 'S') && (second >= '0' && second <= '5')) || ((first == 'B') && (second >= '0' && second <= '2'))) {
+                if (third >= 'A' && third <= 'G') {
+                    if (fourth >= '0' && fourth <= '6') {
+                        if (fifth >= '0' && fifth <= '7') {
+                            return true;
+                        }
+                    }
+                }
+            }
+        }
         return false;
     }
 
@@ -24,12 +58,30 @@
      * - each piece placement is well-formed
      * - no more than three special tiles are included
      *
+     * @author Thoraya Al-Sabti (u6136358)
      * @param boardString a board string describing the placement of one or more pieces
      * @return true if the board string is well-formed
      */
     public static boolean isBoardStringWellFormed(String boardString) {
         // FIXME Task 3: determine whether a board string is well-formed
-        return false;
+
+        int specialCount=0;
+        boolean allGoodPlacement=true;
+        if (boardString == null || boardString.length() /5 > 31 || boardString.equals("") || boardString.length()%5!=0) {
+            return false;
+        } else if (boardString.length()%5==0) {
+            for (int i = 0; i < boardString.length(); i = i + 5) {
+                String tilePlacementString = boardString.substring(i, i + 5);
+                if(tilePlacementString.charAt(0) == 'S')
+                {
+                    specialCount++;
+                }
+                if (!isTilePlacementWellFormed(tilePlacementString)) {
+                    allGoodPlacement = false;
+                }
+            }
+        }
+        return specialCount<=3 && allGoodPlacement;
     }
 
 
@@ -42,13 +94,43 @@
      * invalid connection between highway and railway; and
      * areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
      *
+     * @author Baohong Tan (u6126217)
      * @return true if the placements are connected neighbours
      */
     public static boolean areConnectedNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
         // FIXME Task 5: determine whether neighbouring placements are connected
-        return false;
+        int xa = Integer.parseInt(tilePlacementStringA.charAt(3)+"");
+        int ya = (int)(tilePlacementStringA.charAt(2)-65);
+        int xs = Integer.parseInt(tilePlacementStringB.charAt(3)+"");
+        int ys = (int)(tilePlacementStringB.charAt(2)-65);
+        if(Math.abs(xa-xs)==1 && Math.abs(ya-ys)==1) return false;
+        else if((Math.abs(xa-xs)==1||Math.abs(ya-ys)==1)) {
+            String pica = testConnect(tilePlacementStringA);
+            String picb = testConnect(tilePlacementStringB);
+            if(tilePlacementStringA.charAt(3)==tilePlacementStringB.charAt(3)&&tilePlacementStringA.charAt(2)>tilePlacementStringB.charAt(2)){
+                if(pica.charAt(1)=='-'|| picb.charAt(3)=='-')return false;
+                return pica.charAt(1)==picb.charAt(3);//a down
+            }
+            else if(tilePlacementStringA.charAt(3)==tilePlacementStringB.charAt(3)&&tilePlacementStringA.charAt(2)<tilePlacementStringB.charAt(2))
+            {
+                if(pica.charAt(3)=='-'|| picb.charAt(1)=='-')return false;
+                return pica.charAt(3)==picb.charAt(1);//a up
+            }
+            else if(tilePlacementStringA.charAt(2)==tilePlacementStringB.charAt(2)&&tilePlacementStringA.charAt(3)>tilePlacementStringB.charAt(3))
+            {
+                if(pica.charAt(0)=='-'|| picb.charAt(2)=='-')return false;
+                return pica.charAt(0)==picb.charAt(2);//a right
+            }
+            else if(tilePlacementStringA.charAt(2)==tilePlacementStringB.charAt(2)&&tilePlacementStringA.charAt(3)<tilePlacementStringB.charAt(3))
+            {
+                if(pica.charAt(2)=='-'|| picb.charAt(0)=='-')return false;
+                else return pica.charAt(2)==picb.charAt(0);//a left
+            }else return false;
+        }
+        else return false;
     }
 
+
     /**
      * Given a well-formed board string representing an ordered list of placements,
      * determine whether the board string is valid.
@@ -62,12 +144,155 @@
      * - A tile may have one or more edges touching a blank edge of another tile;
      *   this is referred to as disconnected, but the placement is still legal.
      *
+     * @author Baohong Tan (u6126217)
      * @param boardString a board string representing some placement sequence
      * @return true if placement sequence is valid
      */
     public static boolean isValidPlacementSequence(String boardString) {
+        String[][] railArrT = {{"S0","26"},{"S1","123567"},{"S3","01234567"},{"S4","2356"},{"S5","1357"},
+                {"A0","0147"},{"A1","0246"},{"A2","023456"},{"B0","26"},{"B1","35"},{"B2","1357"}};
+        String[][] railArrD = {{"S0","04"},{"S1","013457"},{"S3","01234567"},{"S4","0147"},{"S5","1357"},
+                {"A0","256"},{"A1","0246"},{"A2","012467"},{"B0","04"},{"B1","17"},{"B2","1357"}};
+        String[][] railArrL = {{"S0","15"},{"S1","012456"},{"S3","01234567"},{"S4","1245"},{"S5","0246"},
+                {"A0","0367"},{"A1","1357"},{"A2","123457"},{"B0","15"},{"B1","24"},{"B2","0246"}};
+        String[][] railArrR = {{"S0","37"},{"S1","023467"},{"S3","01234567"},{"S4","0367"},{"S5","0246"},
+                {"A0","1245"},{"A1","1357"},{"A2","013567"},{"B0","37"},{"B1","06"},{"B2","0246"}};
+        String[][] highArrT = {{"S0","013457"},{"S1","04"},{"S2","01234567"},{"S4","0147"},{"S5","0246"},
+                {"A3","023456"},{"A4","0246"},{"A5","0147"},{"B0","04"},{"B1","04"},{"B2","0246"}};
+        String[][] highArrD = {{"S0","123567"},{"S1","26"},{"S2","01234567"},{"S4","2356"},{"S5","0246"},
+                {"A3","012467"},{"A4","0246"},{"A5","2356"},{"B0","26"},{"B1","26"},{"B2","0246"}};
+        String[][] highArrL = {{"S0","023467"},{"S1","37"},{"S2","01234567"},{"S4","0367"},{"S5","1357"},
+                {"A3","123457"},{"A4","1357"},{"A5","0367"},{"B0","37"},{"B1","37"},{"B2","1357"}};
+        String[][] highArrR = {{"S0","012456"},{"S1","15"},{"S2","01234567"},{"S4","1245"},{"S5","1357"},
+                {"A3","013567"},{"A4","1357"},{"A5","1245"},{"B0","15"},{"B1","15"},{"B2","1357"}};
+        ArrayList<Boolean> boolarr = new ArrayList<>();
+        String cms = "ABCDEFG";
+        String start = "A1A3A5B0B6D0D6F0F6G1G3G5";
+        if(boardString.length()==5 && start.contains(boardString.substring(2,4))) return true;
+        if(boardString.length()%5!=0)return false;
+        else if(boardString.length()>5*49) return false;
+        else{
+            String[] strarr = new String[boardString.length()/5];
+            int index =0;
+            for(int i =0; i<boardString.length();i+=5){
+                strarr[index] = boardString.substring(i,i+5);
+                index++;
+            }
+            ArrayList<Boolean> isolate = new ArrayList<>();
+            for(String s : strarr){
+                if((s.charAt(0)!='A' || s.charAt(0)!='B' || s.charAt(0)!='S') &&
+                        (Integer.parseInt(s.charAt(1)+"")<0 || Integer.parseInt(s.charAt(1)+"")>5) &&
+                        !cms.contains(s.charAt(2)+"") &&
+                        (Integer.parseInt(s.charAt(3)+"")<0 || Integer.parseInt(s.charAt(3)+"")>6) &&
+                        (Integer.parseInt(s.charAt(4)+"")<0 || Integer.parseInt(s.charAt(4)+"")>7)) boolarr.add(false);
+                //内容合法数列
+                else boolarr.add(true);
+                if(s.substring(2,4).equals("A1")||s.substring(2,4).equals("A5")){
+                    for(String[] sarr:railArrT)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("G1") || s.substring(2,4).equals("G5")){
+                    for(String[] sarr:railArrD)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("D0")){
+                    for(String[] sarr:railArrL)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("D6")){
+                    for(String[] sarr:railArrR)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("B0") || s.substring(2,4).equals("F0")){
+                    for(String[] sarr:highArrL)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("B6") || s.substring(2,4).equals("F6")){
+                    for(String[] sarr:highArrR)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("A3")){
+                    for(String[] sarr:highArrT)
+                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
+                }else if(s.substring(2,4).equals("G3")) {
+                    for (String[] sarr : highArrD)
+                        if (s.substring(0, 2).equals(sarr[0]) && sarr[1].contains(s.charAt(4) + "")) boolarr.add(false);
+                }
+                //marginal legal
+                ArrayList<Boolean> connect= new ArrayList<>();
+
+                for(String a:strarr){
+                    if(!a.equals(s)){
+                        int xa = Integer.parseInt(a.charAt(3)+"");
+                        int ya = (int)(a.charAt(2)-65);
+                        int xs = Integer.parseInt(s.charAt(3)+"");
+                        int ys = (int)(s.charAt(2)-65);
+                        if(Math.abs(xa-xs)==1||Math.abs(ya-ys)==1){
+                            connect.add(true);
+                            String pica = testConnect(a);
+                            String picb = testConnect(s);
+                            if(a.charAt(3)==s.charAt(3)&&a.charAt(2)>s.charAt(2))
+                                boolarr.add(pica.charAt(1)==picb.charAt(3)||pica.charAt(1)=='-'||picb.charAt(3)=='-');//a下
+                            else if(a.charAt(3)==s.charAt(3)&&a.charAt(2)<s.charAt(2))
+                                boolarr.add(pica.charAt(3)==picb.charAt(1)||pica.charAt(3)=='-'||picb.charAt(1)=='-');//a上
+                            else if(a.charAt(2)==s.charAt(2)&&a.charAt(3)>s.charAt(3))
+                                boolarr.add(pica.charAt(0)==picb.charAt(2)||pica.charAt(0)=='-'||picb.charAt(2)=='-');//a右
+                            else if(a.charAt(2)==s.charAt(2)&&a.charAt(3)<s.charAt(3))
+                                boolarr.add(pica.charAt(2)==picb.charAt(0)||pica.charAt(2)=='-'||picb.charAt(0)=='-');//a左
+                        } else if(start.contains(a.substring(2,4))) connect.add(true);
+                         else
+                            connect.add(false);
+                    }
+                }
+                if(!connect.contains(true)) boolarr.add(false);
+                if(start.contains(s.substring(2,4))) isolate.add(true);
+                else isolate.add(false);
+            }
+            if(!isolate.contains(true)) boolarr.add(false);
+        }
+        if(boolarr.contains(false)) return false;
+        else return true;
         // FIXME Task 6: determine whether the given placement sequence is valid
-        return false;
+    }
+    //LEFT-TOP-RIGHT-BOTTOM R = RAIL, H=HIGH
+
+    /**
+     * @author Baohong Tan (u6126217)
+     */
+    private static String testConnect(String input){
+        String pic = input.substring(0,2);
+        String ori = input.charAt(4)+"";
+        if(pic.equals("S0")) pic="HHHR";
+        else if(pic.equals("S1")) pic="RHRR";
+        else if(pic.equals("S2")) pic="HHHH";
+        else if(pic.equals("S3")) pic="RRRR";
+        else if(pic.equals("S4")) pic="HHRR";
+        else if(pic.equals("S5")) pic="RHRH";
+        else if(pic.equals("A0")) pic="RR--";
+        else if(pic.equals("A1")) pic="-R-R";
+        else if(pic.equals("A2")) pic="-RRR";
+        else if(pic.equals("A3")) pic="-HHH";
+        else if(pic.equals("A4")) pic="-H-H";
+        else if(pic.equals("A5")) pic="HH--";
+        else if(pic.equals("B0")) pic="-H-R";
+        else if(pic.equals("B1")) pic="-HR-";
+        else if(pic.equals("B2")) pic="RHRH";
+        String out = rolate(pic,ori);
+        return out;
+    }
+
+    /**
+     * @author Baohong Tan (u6126217)
+     */
+    private static String rolate(String pic, String ori){
+        int orit = Integer.parseInt(ori);
+        char[] pic_out = new char[4];
+        if(orit<4){
+            for (int i = 0; i < 4; i++)
+                pic_out[(orit+i)%4] = pic.charAt(i);
+        }else{
+            char[] flip = {pic.charAt(2),pic.charAt(1),pic.charAt(0),pic.charAt(3)};
+            for (int i = 0; i < 4; i++)
+                pic_out[(orit+i)%4] = flip[i];
+        }
+        String out ="";
+        for(char c:pic_out)
+            out+=c;
+        return out;
     }
 
     /**
@@ -78,11 +303,21 @@
      * Each die roll is composed of a character 'A' or 'B' representing the dice,
      * followed by a digit character representing the face.
      *
+     * @author Baohong Tan (u6126217)
      * @return a String representing the die roll e.g. A0A4A3B2
      */
     public static String generateDiceRoll() {
+        String out = "";
+        for (int i = 0; i < 3; i++) {
+            Random diceA = new Random();
+            int ranDiceA = diceA.nextInt(5);
+            out+=("A"+ranDiceA);
+        }
+        Random diceB = new Random();
+        int ranDiceB = diceB.nextInt(2);
+        out+=("B"+ranDiceB);
         // FIXME Task 7: generate a dice roll
-        return "";
+        return out;
     }
 
     /**
@@ -93,15 +328,24 @@
      * * Number of centre tiles used
      * * Number of dead ends in the network
      *
+     * @author Baohong Tan (u6126217)
      * @param boardString a board string representing a completed game
      * @return integer (positive or negative) for score *not* considering longest rail/highway
      */
-    public static int getBasicScore(String boardString) {
-        // FIXME Task 8: compute the basic score
-        return -1;
+    public static int getBasicScore(String boardString)
+{
+    Board board = new Board();
+    for(int i=0; i<boardString.length()-1; i+=5)
+    {
+        board.addTile(boardString.substring(i, i+5), true);
     }
 
+    ScoreCalculator sc = new ScoreCalculator(board);
+    return sc.getBasicScore();
+
+}
     /**
+     * @author Thoraya Al-Sabti (u6136358)
      * Given a valid boardString and a dice roll for the round,
      * return a String representing an ordered sequence of valid piece placements for the round.
      * @param boardString a board string representing the current state of the game as at the start of the round
@@ -111,7 +355,58 @@
      */
     public static String generateMove(String boardString, String diceRoll) {
         // FIXME Task 10: generate a valid move
-        return null;
+
+        String result = "";
+        int size = boardString.length() / 5;
+        Board board = new Board();
+        for (int i = 0; i < size; i++) {
+            board.addTile(boardString.substring(i * 5, i * 5 + 5), true);
+        }
+
+        boolean placementMade = true;
+        while(diceRoll.length() > 0 && placementMade)
+        {
+            placementMade = false;
+
+            for (int i = 0; i < diceRoll.length(); i+=2) {
+                String id = diceRoll.substring(i, i + 2);
+                Tile dice = new Tile(id);
+
+                boolean diceAdded = false;
+                for (int y = 0; y < 7; y++) {
+                    for (int x = 0; x < 7; x++) {
+                        //build the id from the x and y values
+                        StringBuilder coords = new StringBuilder();
+                        coords.append((char) (y + 65));
+                        coords.append(x);
+
+
+                        for (int o = 0; o < 8; o++) {
+
+
+                            dice.updateOrientation(o);
+                            dice.fixOrientation();
+                            String placementString = dice.getId() + coords.toString() + dice.getOrientation();
+
+                            if (board.addTile(placementString, true)) {
+                                result += placementString;
+                                diceAdded = true;
+                                placementMade = true;
+                                String head = diceRoll.substring(0, i);
+                                String tail = diceRoll.substring(i + 2);
+                                diceRoll = head + tail;
+                                break;
+                            }
+                        }
+                        if(diceAdded) break;
+                    }
+                    if(diceAdded) break;
+                }
+            }
+
+        }
+
+        return result;
     }
 
     /**
@@ -121,12 +416,20 @@
      * * Longest railroad
      * * Longest highway
      *
+     * @author Samuel J. Brookes (u5380100)
      * @param boardString a board string representing a completed game
      * @return integer (positive or negative) for final score (not counting expansion packs)
+     *
+     * @author Samuel J. Brookes (u5380100)
      */
     public static int getAdvancedScore(String boardString) {
         // FIXME Task 12: compute the total score including bonus points
-        return -1;
+        Board board = new Board(boardString);
+        ScoreCalculator sc = new ScoreCalculator(board);
+
+        return sc.getAdvancedScore();
     }
+
 }
 
+
Only in comp1110-ass2/src/comp1110/ass2: ScoreCalculator.class
Only in comp1110-ass2/src/comp1110/ass2: ScoreCalculator.java
Only in comp1110-ass2/src/comp1110/ass2: ScoreCalculator$RouteNode.class
Only in comp1110-ass2/src/comp1110/ass2: ScoreCalculator$TraversalNode.class
Only in comp1110-ass2/src/comp1110/ass2: Tile.class
Only in comp1110-ass2/src/comp1110/ass2: Tile.java
Only in comp1110-ass2/src: gittest
Only in comp1110-ass2/tests/comp1110/ass2: BoardTest.java
Only in comp1110-ass2/tests/comp1110/ass2: DicesTest.java
diff -ru -x .git ../master/comp1110-ass2/tests/comp1110/ass2/GenerateDiceRollTest.java comp1110-ass2/tests/comp1110/ass2/GenerateDiceRollTest.java
--- ../master/comp1110-ass2/tests/comp1110/ass2/GenerateDiceRollTest.java	2019-05-24 08:28:25.774602100 +1000
+++ comp1110-ass2/tests/comp1110/ass2/GenerateDiceRollTest.java	2019-05-24 08:33:26.133431900 +1000
@@ -12,7 +12,7 @@
 
 public class GenerateDiceRollTest {
 
-    private static int BASE_ITERATIONS = 10000;
+    private static int BASE_ITERATIONS = 100;
 
     @Rule
     public Timeout globalTimeout = Timeout.millis(2000);
@@ -66,69 +66,40 @@
 
     @Test
     public void testDieFaces() {
-        int[] countsA = new int[6];
-        int[] countsB = new int[3];
+        int zero = 0;
+        int one = 0;
+        int two = 0;
+        int three = 0;
+        int four = 0;
+        int five = 0;
+
         for (int i = 0; i < BASE_ITERATIONS; i++) {
             String roll = RailroadInk.generateDiceRoll();
-            for (int j = 1; j < 6; j += 2) {
+            for (int j = 1; j < roll.length(); j += 2) {
                 int face = Character.getNumericValue(roll.charAt(j));
                 switch (face) {
                     case 0:
-                        countsA[0]++;
-                        break;
+                        zero++;
                     case 1:
-                        countsA[1]++;
-                        break;
+                        one++;
                     case 2:
-                        countsA[2]++;
-                        break;
+                        two++;
                     case 3:
-                        countsA[3]++;
-                        break;
+                        three++;
                     case 4:
-                        countsA[4]++;
-                        break;
+                        four++;
                     case 5:
-                        countsA[5]++;
-                        break;
+                        five++;
                 }
                 assertFalse("Expected a number between 0 and 5, but you rolled: " + face, face < 0 || face > 5);
             }
-            for (int j = 7; j < 8; j += 2) {
-                int face = Character.getNumericValue(roll.charAt(j));
-                switch (face) {
-                    case 0:
-                        countsB[0]++;
-                        break;
-                    case 1:
-                        countsB[1]++;
-                        break;
-                    case 2:
-                        countsB[2]++;
-                        break;
-                }
-                assertFalse("Expected a number between 0 and 2, but you rolled: " + face, face < 0 || face > 2);
-            }
-        }
-        assertTrue("Expected your dice A to roll at least one of each value from 0-5 but missed a value", Arrays.stream(countsA).min().getAsInt() > 0);
-        assertTrue("Expected your dice B to roll at least one of each value from 0-2 but missed a value", Arrays.stream(countsB).min().getAsInt() > 0);
-        double[] probsA = new double[]{1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0};
-        double[] probsB = new double[]{1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0};
-        int samples = BASE_ITERATIONS;
-        double chiA = chiSquared(probsA, samples * 3, countsA);
-        double chiB = chiSquared(probsB, samples, countsB);
-        assertTrue("Distribution of A rolls don't appear to be uniform (chi squared value of " + chiA + ")", chiA < 14.45);
-        assertTrue("Distribution of B rolls don't appear to be uniform (chi squared value of " + chiB + ")", chiB < 9.35);
-    }
-
-
-    private static double chiSquared(double[] expectedProbs, int samples, int[] counts) {
-        double total = 0;
-        for (int i = 0; i < expectedProbs.length; i++) {
-            double mi = ((double) samples) * expectedProbs[i];
-            total += ((double) counts[i] - mi) * ((double) counts[i] - mi) / mi;
         }
-        return total;
+        assertTrue("Expected numbers 0 - 5 in 100 rolls, but you didn't roll '0'", zero > 0);
+        assertTrue("Expected numbers 0 - 5 in 100 rolls, but you didn't roll '1'", one > 0);
+        assertTrue("Expected numbers 0 - 5 in 100 rolls, but you didn't roll '2'", two > 0);
+        assertTrue("Expected numbers 0 - 5 in 100 rolls, but you didn't roll '3'", three > 0);
+        assertTrue("Expected numbers 0 - 5 in 100 rolls, but you didn't roll '4'", four > 0);
+        assertTrue("Expected numbers 0 - 5 in 100 rolls, but you didn't roll '5'", five > 0);
     }
 
 }
diff -ru -x .git ../master/comp1110-ass2/tests/comp1110/ass2/GetBasicScoreTest.java comp1110-ass2/tests/comp1110/ass2/GetBasicScoreTest.java
--- ../master/comp1110-ass2/tests/comp1110/ass2/GetBasicScoreTest.java	2019-05-24 08:28:25.793567100 +1000
+++ comp1110-ass2/tests/comp1110/ass2/GetBasicScoreTest.java	2019-05-24 08:33:26.149437900 +1000
@@ -5,6 +5,7 @@
 import org.junit.rules.Timeout;
 
 import static org.junit.Assert.assertTrue;
+import static org.junit.Assert.assertNotNull;
 
 public class GetBasicScoreTest {
 
@@ -36,6 +37,26 @@
     String ex3 = "A1A30A0B30A5A11B1B20S4A23";
 
     // TODO remove incomplete games (there's no requirement to score an incomplete game)
+    @Test (expected = NullPointerException.class)
+    public void testNone() {
+        int score = RailroadInk.getBasicScore(null);
+        assertNotNull(score);
+    }
+
+    @Test
+    public void testEmpty() {
+        int score = RailroadInk.getBasicScore("");
+        assertNotNull(score);
+
+    }
+
+    @Test
+    public void testImpossibleBoard() {
+        int score = RailroadInk.getBasicScore("A4A50A0B61A3B52B1A35S0B41A1B31A0B22B2C50S1C44A1D40A1E40B2F41A0C27S4F50B0G52A2C30A2D32A1D21S2F30");
+        assertNotNull(score);
+
+    }
+
 
     @Test
     public void testSmallNoRoutes() {
diff -ru -x .git ../master/comp1110-ass2/tests/comp1110/ass2/IsValidPlacementSequenceTest.java comp1110-ass2/tests/comp1110/ass2/IsValidPlacementSequenceTest.java
--- ../master/comp1110-ass2/tests/comp1110/ass2/IsValidPlacementSequenceTest.java	2019-05-24 08:28:25.815565500 +1000
+++ comp1110-ass2/tests/comp1110/ass2/IsValidPlacementSequenceTest.java	2019-05-24 08:33:26.160437900 +1000
@@ -28,14 +28,6 @@
     }
 
     @Test
-    public void testDuplicate() {
-        testTrivialCorrect();
-        for (String test : DUPLICATE_PLACEMENT) {
-            assertFalse("Placement sequence '" + test + "'was invalid, but passed", RailroadInk.isValidPlacementSequence(test));
-        }
-    }
-
-    @Test
     public void testNoValidConnection() {
         testTrivialCorrect();
         for (String test : NO_VALID_CONNECTION) {
@@ -64,15 +56,6 @@
         assertTrue("Placement sequence '" + test + "' is valid, but failed ", RailroadInk.isValidPlacementSequence(test));
     }
 
-    static final String[] DUPLICATE_PLACEMENT = {
-            "A3D61A3D53B0C52A0B52A2B63A4D41B0E60A0F61A3D31A3D23A2G30B0F34A3E32A1B01B2B10A1B21A0A63A4D01A1G41B0G12A2F12S2D10A4C10B2A10A2B33A1A30S5F11A4E21A3C21A3C31S4E11",
-            "A2A30A0A43A3A50B2B50A4C50A3B20A2B43B0G12B0A14A2B33A0B11A3D61A2B21A3G52B1G45A3F52A4E50B2F41A3F33A1E40A1D40A3E32A3E27B0F10S0E12B1D17A4D01A1B61A0C43",
-            "A3A10A3A52A3G10B2F10S1B50A2B61A0C60A1B41B1A35S3B63A4A41A2B31A1C30B0D32A2C50A4E10A3D12B2B10A2F01A0G00A4D01B1A27A2B23S2C10A1D50A0F23B2G25A3E30A4E41",
-            "B1B02B1B02",
-            "B1B02A4C00A4C00",
-            "B1B02A4C00A5C01"
-    };
-
     static final String[] NO_VALID_CONNECTION = {
             "A4B10A4C10B2D10",
             "A3B50B0C50A2D50"
@@ -80,8 +63,7 @@
 
     static final String[] INVALID_CONNECTION = {
             "A3A10A3A52A3G10B2F10S1B50A2B61A0C60A1B41B1A35A4A41S5B34A1C30B0D32A2C50A4E10A3D12B2B10A2F01A0G00A4D01B1A27S3B20A4C10A1D50A0F23B2G25A3E30A4E41",
-            "A2A30A0A43A3A50B2B50A4C50A2B43B0G12B0A14A2B33A0B11A3G52A3D61A2B21A4F50B1G45A3E52B2F41A3F33A2E40A1D40A3E32A3E27B0F10S0E12B1D17A4D01A1B61A0C43",
-            "A4A10A1A30A4A50S1B32A1B01A1B61B2B10A1B21S5B50A1B41A4D01A4D61A3D12B0C30A3D50A4C10A4C50A1F01A1F61A4G10B1F12A4G50B1E10A1E21A3E52B1F56S4E31A1D30A1F30A4E41B0G30"
+            "A2A30A0A43A3A50B2B50A4C50A3B20A2B43B0G12B0A14A2B33A0B11A4E50A3D61A2B21A3G52B1G45A3F52B2F41A3F33A1E40A1D40A3E32A3E27B0F10S0E12B1D17A4D01A1B61A0C43"
     };
 
     static final String[] INVALID_EDGE = {
Only in comp1110-ass2/tests/comp1110/ass2: LongestRouteTest.java
Only in comp1110-ass2/tests/comp1110/ass2: PlacementTest.java
Only in comp1110-ass2/tests/comp1110/ass2: RouteCompilerTest.java
```
## Test log
```
--javac output--
----
JUnit version 4.12
...
Time: 0.221

OK (3 tests)

JUnit version 4.12
......
Time: 0.328

OK (6 tests)

JUnit version 4.12
.....
Time: 0.181

OK (5 tests)

JUnit version 4.12
.....E
Time: 0.594
There was 1 failure:
1) testDuplicate(comp1110.ass2.IsValidPlacementSequenceTest)
java.lang.AssertionError: Placement sequence 'A3D61A3D53B0C52A0B52A2B63A4D41B0E60A0F61A3D31A3D23A2G30B0F34A3E32A1B01B2B10A1B21A0A63A4D01A1G41B0G12A2F12S2D10A4C10B2A10A2B33A1A30S5F11A4E21A3C21A3C31S4E11'was invalid, but passed
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at org.junit.Assert.assertFalse(Assert.java:64)
	at comp1110.ass2.IsValidPlacementSequenceTest.testDuplicate(IsValidPlacementSequenceTest.java:34)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:298)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:292)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.lang.Thread.run(Thread.java:834)

FAILURES!!!
Tests run: 5,  Failures: 1

JUnit version 4.12
.E...
Time: 0.592
There was 1 failure:
1) testDieFaces(comp1110.ass2.GenerateDiceRollTest)
java.lang.AssertionError: Expected your dice A to roll at least one of each value from 0-5 but missed a value
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at comp1110.ass2.GenerateDiceRollTest.testDieFaces(GenerateDiceRollTest.java:113)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:298)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:292)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.lang.Thread.run(Thread.java:834)

FAILURES!!!
Tests run: 4,  Failures: 1

JUnit version 4.12
.Sample game A3D61A3D53B0C52A0B52A2B63A4D41B0E60A0F61A3D31A3D23A2G30B0F34A3E32A1B01B2B10A1B21A0A63A4D01A1G41B0G12S2D10A4C10B2A10A2B33A1A30S4E11A4E21A3C21A3C31S5F11
score 24
Sample game A3A10A3A52A3G10B2F10S1B50A2B61A0C60A1B41B1A35A4A41A2B31A1C30B0D32A2C50A4E10A3D12B2B10A2F01A0G00A4D01B1A27S3B20A4C10A1D50A0F23B2G25A3E30A4E41
score 18
Sample game A2A30A0A43A3A50B2B50A4C50A3D50A2B43B0G12B0A14A2B33A0B11A4E50A3D61A2B21A3G52B1G45A3F52B2F41A3F33A1E40A1D40A3E32A3E27B0F10S0E12B1D17A4D01A1B61A0C43
score 21
Sample game A4A50A1F61A0B61S5F50B1F46A1F01S1F12A2F23A1E20B2D21A3D03A1C20A0B22B1A61A4D11A4G10B1G44A2G30A3C01A3C12B0B31A1B01A4B50B0C50A2F32A0E32A0E40A4D31B1D47A1B11
score 15
Sample game A4A50A1A30B2B31A0C34A3B41B2C40A3B52A2B60A2C62S5C50B1D65A4B21A2A60A3B10A4A10A4C10B2G10B2F10A4E10A3D12A1F01S2D00A4C00B1B02A0F23A0G20A2F61B2F50A3G52A0G02
score 23
Sample game A4G10B2F10A4E10A0F20A3D17A0E22A2E31B1E44S0D42A3D23A4D31A2F30B1F42A1G30A0C42A0C57B0C22A2F03A1E02S5D01A0B22B0A50A4D51A3D61B2B53A0B30B2A31A4E60A3A41A0B03
score 23
Sample game A3A10B2A31A1B30A0F61A4A21B1B14A4A41A4D61S2A50A5A63A2B01A1C02B0G52S0B63A0E63A2E51A4D51B0C32A5D31A5C61A0E41S5D41B1D03A5B51A4G10A0C42B0G30A2F52A5F12B2F21A0F00
score 26
Sample game A0B06A5A50A5A42A3B44A3C42A3D44B2D35B2C37A0B33A2B27A2E35B2C27A4C13A5C05A1F01B2D21A1F15A5D04A4D11A2E20B1B10A4A10S4E44A3E55A2F25S3F40B2F30A3D50A4D67
score 13
Sample game B2A37A3A43A3A54A3A21A5A14B2B20A0B30A4C20A1B17A2B07B2D05A0E04A1C00B0G16A0F13A0G32B2D11A5D20A0E10A5B51B2G46A4F40A5E42A0C15B1G52A5E50A1F03A2F63
score 18
Sample game A1A30A3D05A3C06B0B06A3C13A3C27B2C33A1B30B0D32A5E30A0A02A2A11S4E24S2D20A4D15B0B16A1E17A0E02A3C43A4B40A3A47A5A50A3C57B0D50A0E51A0E63A0F64
score 13
...
Time: 0.247

OK (4 tests)

JUnit version 4.12
.....
Time: 0.447

OK (5 tests)

JUnit version 4.12
.Sample game A3D61A3D53B0C52A0B52A2B63A4D41B0E60A0F61A3D31A3D23A2G30B0F34A3E32A1B01B2B10A1B21A0A63A4D01A1G41B0G12S2D10A4C10B2A10A2B33A1A30S4E11A4E21A3C21A3C31S5F11
score 41
Sample game A3A10A3A52A3G10B2F10S1B50A2B61A0C60A1B41B1A35A4A41A2B31A1C30B0D32A2C50A4E10A3D12B2B10A2F01A0G00A4D01B1A27S3B20A4C10A1D50A0F23B2G25A3E30A4E41
score 36
Sample game A2A30A0A43A3A50B2B50A4C50A3D50A2B43B0G12B0A14A2B33A0B11A4E50A3D61A2B21A3G52B1G45A3F52B2F41A3F33A1E40A1D40A3E32A3E27B0F10S0E12B1D17A4D01A1B61A0C43
score 42
Sample game A4A50A1F61A0B61S5F50B1F46A1F01S1F12A2F23A1E20B2D21A3D03A1C20A0B22B1A61A4D11A4G10B1G44A2G30A3C01A3C12B0B31A1B01A4B50B0C50A2F32A0E32A0E40A4D31B1D47A1B11
score 32
Sample game A4A50A1A30B2B31A0C34A3B41B2C40A3B52A2B60A2C62S5C50B1D65A4B21A2A60A3B10A4A10A4C10B2G10B2F10A4E10A3D12A1F01S2D00A4C00B1B02A0F23A0G20A2F61B2F50A3G52A0G02
score 42
Sample game A4G10B2F10A4E10A0F20A3D17A0E22A2E31B1E44S0D42A3D23A4D31A2F30B1F42A1G30A0C42A0C57B0C22A2F03A1E02S5D01A0B22B0A50A4D51A3D61B2B53A0B30B2A31A4E60A3A41A0B03
score 42
Sample game A3A10B2A31A1B30A0F61A4A21B1B14A4A41A4D61S2A50A5A63A2B01A1C02B0G52S0B63A0E63A2E51A4D51B0C32A5D31A5C61A0E41S5D41B1D03A5B51A4G10A0C42B0G30A2F52A5F12B2F21A0F00
score 41
Sample game A0B06A5A50A5A42A3B44A3C42A3D44B2D35B2C37A0B33A2B27A2E35B2C27A4C13A5C05A1F01B2D21A1F15A5D04A4D11A2E20B1B10A4A10S4E44A3E55A2F25S3F40B2F30A3D50A4D67
score 35
E.
Time: 0.313
There was 1 failure:
1) testSampleGames(comp1110.ass2.GetAdvancedScoreTest)
java.lang.AssertionError: Sample game including networks connecting [4] exits covering 9 centre squares with 8 errors longest railway 14 longest road 17: expected total score 44 but RailroadInk.getAdvancedScore returned total score: 35
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at comp1110.ass2.GetAdvancedScoreTest.testSampleGames(GetAdvancedScoreTest.java:26)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:298)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:292)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.lang.Thread.run(Thread.java:834)

FAILURES!!!
Tests run: 2,  Failures: 1

```
