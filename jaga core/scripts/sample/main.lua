function load()
	game:require("resources")

	mainScene = scene:new()
	mainScene:add(background)
	mainScene:add(labelFps)
	mainScene:add(cloud)
	mainScene:add(sun)

	game:add(mainScene)
end

function update()
	if game:isKeyPressed("left") then
		cloud:move(-15,0)
	end

	if game:isKeyPressed("right") then
		cloud:move(15,0)
	end

	if game:isKeyPressed("up") then
		cloud:move(0,-15)
	end

	if game:isKeyPressed("down") then
		cloud:move(0,15)
	end

	labelFps.text = game.fps
end

function pointerDown()
	temp = sprite:new("cloud")
	temp:setCenterAtMiddle()
	temp:setX(pointer.x)
	temp:setY(pointer.y)
	mainScene:add(temp)
end

function pointerUp()
	temp = sprite:new("star")
	temp:setCenterAtMiddle()
	temp:setX(pointer.x)
	temp:setY(pointer.y)
	mainScene:add(temp)
	
	bellSound:play()
end