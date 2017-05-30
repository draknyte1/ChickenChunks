package codechicken.chunkloader;

public abstract class TileChunkLoader extends TileChunkLoaderBase
{    
    
    public int countLoadedChunks()
    {
        return getChunks().size();
    }   
    
    @Override
    public void activate()
    {
        if(radius == 0)
        {
            //create a small one and try and increment it to 2   
            radius = 1;
            //shape = ChunkLoaderShape.Square;         
            //setShapeAndRadius(ChunkLoaderShape.Square, 2);
        }
        
        super.activate();
    }
    
    public int radius;
}
