Reviewer: Samuel Brookes (u5380100)
Component: RailroadInk.areConnectedNeighbours()
Author: Baohong Tan (u6126217)

Review Comments:

1.  Good use of methods: 
    * Rather than putting the entirety of the code into a single method, you have created other methods that you call.
    * For example: 
        * testConnect(tilePlacementStringA) @ line 108
        * testConnect(tilePlacementStringB) @ line 109
        * rolate(pic, ori) @ line 274
    * This is excellent as makes the code easier to follow and reduces repetition. 
2.  Variable naming: 
    * You could give your variables more meaningful names as this might make it easier to understand their purpose.
    * For example: 
        * 'xa' could be 'columnA' (@ line 102)
        * 'ya' could be 'rowA' (@ line 103)
        * 'xs' could be 'columnB' (@ line 104)
        * 'ys' could be 'rowB' (@ line 105)
        * 'pica' could be 'pieceA' (@ line 108)
        * 'picb' coulb be 'pieceB' (@ line 109)
    * This might make the code easier to follow as you can quickly discern the purpose of a variable as you read.
3.  Commenting: 
    * A description for the accessory methods might make it easier to understand what their intended purpose is.
    * For example:
        * The testConnect() method has no description (@ line 256)
        * The rolate() method has no description (@ line 281)
    * It would also be very useful to have more comments inserted throughout the methods themselves. As it can be very slow to read and understand code without them.
    * For example: 
        * if(Math.abs(xa-xs)==1 && Math.abs(ya-ys)==1) return false; (@ line 106) could have a comment like //if the pieces are positioned diagonally in relation to each other then they are not connected
    * Additionally, the existing comments in the code could be extended a little to include more information.
    * For example: 
        * return pica.charAt(1)==picb.charAt(3);//a down (@ line 112) could read //Piece A is above Piece B
    * This might make it a little easier for someone else reading the code to understand what is happening.
4.  Redundancy: 
    * There were some pre-existing classes that could've been used to reduce some redundancy.
    * For example: 
        * The Placement class can enable easy access to components of a placement string.
        * Rather than constantly referencing characters in a String as you do, for example, at line 110, 114, 119 and 124. You could make it a little easier to read by creating two Placement objects and doing something like the following (for example):
            * @ line 110: if(placementA.getColumn() == placementB.getColumn() && placementA.getRowAsInt() > placementB.getRowAsInt())
        * While this still takes roughly the same amount of characters to type, it is easier to understand because the methods have meaningful names.

 