package com.mygdx.escapefromlannioncity.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe d'objet du jeu
 * a utiliser pour toute zone interagissable fixe
 */
public class GameObject {

    public Sprite sprite;
    private Texture texture;
    private final Texture emptyTexture = new Texture(Gdx.files.internal("image/Utilitaire/empty.png"));
    public Rectangle hitbox;
    float scaleX = 1;
    float scaleY = 1;
    private boolean hidden;
    public String name;

    public GameObject(){
        this.sprite = new Sprite(this.emptyTexture);
        hitbox = new Rectangle();
        name = "";
    }

    public GameObject(FileHandle sprite, float width, float height, String name){
        this.texture = new Texture(sprite);
        hitbox = new Rectangle();
        this.sprite = new Sprite(this.texture);
        hitbox.setSize(width, height);
        hitbox.setPosition(0, 0);
        this.name = name;
    }

    public GameObject(FileHandle sprite, Vector2 centerPos, float width, float height, String name){
        this.texture = new Texture(sprite);
        hitbox = new Rectangle();
        this.sprite = new Sprite(this.texture);
        hitbox.setSize(width, height);
        hitbox.setCenter(centerPos);
        this.name = name;
    }

    public GameObject(FileHandle sprite, float x, float y, float width, float height, String name){
        this.texture = new Texture(sprite);
        hitbox = new Rectangle();
        this.sprite = new Sprite(this.texture);
        hitbox.setSize(width, height);
        hitbox.setCenter(x, y);
        hidden = false;
        this.name = name;
    }

    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public Vector2 getCenterPos(){
        Vector2 temp = new Vector2();
        hitbox.getCenter(temp);
        return temp;
    }

    public float getX(){
        return hitbox.getX();
    }

    public float getY(){
        return hitbox.getY();
    }

    public float getWidth(){
        return hitbox.getWidth();
    }

    public float getHeight(){
        return hitbox.getHeight();
    }

    /**
     * Permet de savoir si il y a collision entre un point et le GameObject
     * @param point : point a tester
     * @return vrai si il y a collision, faux sinon
     */
    public boolean contains(Vector2 point){
        return hitbox.contains(point);
    }

    /**
     * Permet de savoir si il y a collision entre un point et le GameObject
     * @param x : absisse du point
     * @param y : ordonne du point
     * @return vrai si il y a collision, faux sinon
     */
    public boolean contains(float x, float y){
        return hitbox.contains(x,y);
    }

    /**
     * Permet de savoir si il y a un Rectangle dans le GameObject
     * @param object : rectangle a tester
     * @return vrai si il y a contenance, faux sinon
     */
    public boolean contains(Rectangle object){
        return hitbox.contains(object);
    }

    /**
     * Permet de savoir si il y a un cercle dans le GameObject
     * @param object : cercle a tester
     * @return vrai si il y a contenance, faux sinon
     */
    public boolean contains(Circle object){
        return hitbox.contains(object);
    }

    /**
     * Permet de savoir si il y a une intersection entre un rectangle et le GameObject
     * @param object rectangle a tester
     * @return vrai si il y a intersection, faux sinon
     */
    public boolean overlaps(Rectangle object){
        return hitbox.overlaps(object);
    }


    /**
     * Permet de changer la position du centre d'un GameObject
     * @param centerPos nouvelle position
     */
    public void setCenterPos(Vector2 centerPos){
        hitbox.setCenter(centerPos);
    }

    /**
     * Permet de changer la position du centre d'un GameObject
     * @param x : abscisse
     * @param y : ordonne
     */
    public void setCenterPos(float x, float y){
        hitbox.setCenter(x, y);
    }

    /**
     * permet de changer la taille d'un GameObject
     * @param width : largeur
     * @param height : hauteur
     */
    public void setSize(float width, float height){
        hitbox.setSize(width, height);
    }

    /**
     * Affiche un sprite a l'emplacement de sa hitbox
     * @param batch : le SpriteBatch dans lequel on doit afficher le GameObject
     */
    public void drawFix(SpriteBatch batch){
        batch.draw(sprite, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    /**
     * permet d'affecter un facteur d'echelle a un GameObject
     * pour qu'il soit coherent avec la taille de l'ecran
     * NE PAS APPELER DANS resize() !
     */
    public void resize(){
        int baseWorldWidth = 256;
        int baseWorldHeight = 144;
        int actualWorldWidth = Gdx.graphics.getWidth();
        int actualWorldHeight = Gdx.graphics.getHeight();

        scaleX = actualWorldWidth/(baseWorldWidth*scaleX);
        scaleY = actualWorldHeight/(baseWorldHeight*scaleY);

        hitbox.setSize(hitbox.width*scaleX, hitbox.height*scaleY);
        hitbox.setPosition(hitbox.x*scaleX, hitbox.y*scaleY);

    }

    public void dispose(){
        texture.dispose();
        emptyTexture.dispose();
    }

    /**
     * Cache le sprite d'origine du GameObject
     */
    public void hide(){
        this.sprite.setRegion(emptyTexture);
        hidden = true;
    }

    /**
     * Affiche le sprite d'origine du GameObject
     */
    public void unhide(){
        this.sprite.setRegion(texture);
        hidden = false;
    }

    /**
     * Verifie si le sprite d'origine est cache ou non
     * @return true si le sprite d'origine n'est pas affiche, false sinon
     */
    public boolean isHidden(){
        return hidden;
    }

    public String getName(){
        return name;
    }
}
