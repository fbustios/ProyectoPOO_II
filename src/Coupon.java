public abstract class Coupon {
    protected String nombre;
    protected int spawnLevel;
    protected boolean AF;
    protected boolean activo;

    public abstract void marcar(Coordenada a, boolean t);

    public String getNombre(){return nombre;}
    public int getSpawnLevel(){return spawnLevel;}
    public boolean getAF(){return AF;}
    public boolean getActivo(){return activo;}
}
