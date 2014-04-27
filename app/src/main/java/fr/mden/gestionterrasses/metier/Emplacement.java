package fr.mden.gestionterrasses.metier;

public class Emplacement
{
    private int id;
    private int coordX;
    private int coordY;
    private String rue1;
    private String rue2;
    private String ville;
    private int superficie;
    private int nbPlacesParking;

    /**
     * Constructeur de la classe Emplacement
     *
     * @param coordX
     * @param coordY
     * @param rue1
     * @param rue2
     * @param ville
     * @param superficie
     * @param nbPlacesParking
     */
    public Emplacement(int coordX, int coordY, String rue1, String rue2, String ville, int superficie, int nbPlacesParking) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.rue1 = rue1;
        this.rue2 = rue2;
        this.ville = ville;
        this.superficie = superficie;
        this.nbPlacesParking = nbPlacesParking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public String getRue1() {
        return rue1;
    }

    public void setRue1(String rue1) {
        this.rue1 = rue1;
    }

    public String getRue2() {
        return rue2;
    }

    public void setRue2(String rue2) {
        this.rue2 = rue2;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getNbPlacesParking() {
        return nbPlacesParking;
    }

    public void setNbPlacesParking(int nbPlacesParking) {
        this.nbPlacesParking = nbPlacesParking;
    }
}