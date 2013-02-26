2009-12-09


make desktop version work 

save scripts in /scripts folder

todo list

1. add to gameobject 
	- visible
	- removed set true by remove()
	  and used by the cleanup method after update()
	- alpha  
	- rotation
	  
2. add GameCamera

3. add elapsed time as fraction of 1 sec to elapsed 

4. add Effect class with following subclasses

	- FadeOut
	- FadeIn
	- Movement
	
5. create setup file with the following ;

	- game title
	- game icon
	- window size
	
6. add font face and size to Label

7. add animation and state to Sprite

8. set scene id when creating the scene in the lua script

9. the DesktopLogger should save the log in a local file

10. create test App with multiples scenes

11. add collision manager to the core 

12. check if the events structure is need or will be enough, 
to trigger the events in the environment class

13. check the class used to draw the FastCanvas in the Desktop impl
if is expecting the GUI elements or its just drawing. 
 	 	  