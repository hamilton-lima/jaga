	game:debug("resources.lua" )

	cloud = sprite:new("cloud")
	cloud:setCenterAtMiddle()
	cloud:setX(50)
	cloud:setY(50)

	sun = sprite:new("sun")
	sun:add("sun1")
	sun:add("sun2")
	sun:add("sun3")
	sun:setCenterAtMiddle()
	sun:setX(150)
	sun:setY(150)

	background = sprite:new("background")
	
	labelFps = label:new("fps", 10, 20, "")
	
	backgroundSound = audio:new("soundtrack")
	backgroundSound:loop()

	bellSound = audio:new("cowbell")
