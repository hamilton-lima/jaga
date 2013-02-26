gameClassName = "com.athanazio.jaga.core.Game"
sceneClassName = "com.athanazio.jaga.core.Scene"
spriteClassName = "com.athanazio.jaga.core.Sprite"
audioClassName = "com.athanazio.jaga.core.Audio"
labelClassName = "com.athanazio.jaga.core.Label"

gameClass = luajava.bindClass( gameClassName )
game = gameClass:getInstance()
pointer = game:getPointer()

scene = {} 
function scene:new()
	return luajava.newInstance( sceneClassName ) 
end

sprite = {}
function sprite:new(id)
	localSprite = luajava.newInstance( spriteClassName )
	localSprite:setId(id)
	localSprite:add(id)
	return localSprite
end

audio = {}
function audio:new(id)
	localAudio = luajava.newInstance( audioClassName )
	localAudio:setId(id)
	return localAudio
end

label = {}
function label:new(id,x,y,text)
	localLabel = luajava.newInstance( labelClassName )
	localLabel:setId(id)
	localLabel:setX(x)
	localLabel:setY(y)
	localLabel:setText(text)
	return localLabel
end

