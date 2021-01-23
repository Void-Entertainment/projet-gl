package com.mygdx.escapefromlannioncity.identify;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.escapefromlannioncity.EscapeFromLannionCity;
import com.mygdx.escapefromlannioncity.menu.ButtonOpenMenu;
import com.mygdx.escapefromlannioncity.siteweb.GetJoueur;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class Login implements Screen {
    private final EscapeFromLannionCity game;

    private final Viewport viewport;
    private final Sprite background;

    // le stage
    private final Stage stageLogin;

    //les éléments du stage
    private final TextField nameText;
    private final TextField MdpText;
    private final TextButton button;
    private final Label message;
    private final TextButton retour;


    public Login(final EscapeFromLannionCity pGame) {

        this.game = pGame;
        Texture menuing = new Texture(Gdx.files.internal("image/Utilitaire/blacksquare.png"));
        // place une camera dans la vue actuelle de la fenêtre
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(camera);
        viewport.apply(true);



        //le stage
        this.stageLogin = new Stage(viewport);

        //les éléments de style
        BitmapFont nbb=game.mainFont.newFontCache().getFont();
        TextField.TextFieldStyle Tstyle= new TextField.TextFieldStyle(nbb,WHITE,
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("image/Utilitaire/cursortxt.png")))),
                null, new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("image/Utilitaire/textinput.png")))));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(null,null,null,nbb);
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("image/Utilitaire/bouttontxt.png"))));
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("image/Utilitaire/bouttontxt.png"))));
        style.checked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("image/Utilitaire/bouttontxt.png"))));
        style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("image/Utilitaire/bouttontxt.png"))));
        Label.LabelStyle rr=new Label.LabelStyle(nbb,WHITE);

        // LOG IN
        Label nameLabel = new Label("Email :", rr);
        this.nameText = new TextField("", Tstyle);
        Label addressLabel = new Label("Mot de passe :", rr);
        this.MdpText = new TextField("", Tstyle);
        this.MdpText.setPasswordMode(true);
        this.message = new Label("",rr);
        this.button = new TextButton("SE CONNECTER", style);
        this.retour = new TextButton("RETOUR", style);

        //le tableau pour la structure spatiale
        Table table1 = new Table();
        table1.add(retour).padLeft(10).padTop(15).maxWidth(150);
        table1.setFillParent(true);
        table1.left().top();

        Table table = new Table();
        table.add(nameLabel).uniform().padBottom(20);
        table.add(nameText).prefWidth(500).uniform().padBottom(20);
        table.row();
        table.add(addressLabel).padBottom(50);
        table.add(MdpText).width(500).padBottom(50);
        table.row();
        table.add(button).colspan(2).padBottom(50).minWidth(100);
        table.row();
        table.add(this.message).colspan(2);

        //ajout du background
        table.setBackground(new TextureRegionDrawable(new TextureRegion(menuing)));
        background = new Sprite(menuing);

        //la position du tableau
        table.setFillParent(true);
        table.center();

        //ajout au stage
        stageLogin.addActor(table);
        stageLogin.addActor(table1);


    }

    public void goGoGadgettoMenu(Vector2 pTouched, ButtonOpenMenu pButton) {

        if (pButton.isMyButton(pTouched)) {
            //pScreen.hide();
            /*this.render(delta);*/
            game.setScreen(this);

        }

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stageLogin);
    }

    @Override
    public void render(float delta) {

        // Screen screenPrecedent = game.getScreen();

        game.batch.begin();

        /* Nettoie le fond de l'écran */
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //lance le stage
        stageLogin.draw();
        stageLogin.act();

      //  game.batch.draw(background.getTexture(), 0,0, viewport.getWorldWidth(), viewport.getWorldHeight());


        game.batch.end();

        /* Check pour un clic gauche de la souris */
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            // prend les coordonnees du clic et les convertit en coordonnees du monde
            String res;
            Vector2 touched = new Vector2();
            touched.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touched);
            if (background.getTexture().toString().matches("image/Utilitaire/blacksquare.png")) {

                if(this.retour.isPressed()){
                    //retour à l'écran d'accueil
                    game.setScreen(game.menuEtTableau[2]);
                }
                if (button.isPressed()) {
                    //si l'utilisateur appuie sur se connecter
                    System.out.println(this.nameText.getText());
                    System.out.println(this.MdpText.getText());
                    //tous les champs doivent être remplis
                    if(this.nameText.getText().equals("") ||
                            this.MdpText.getText().equals("")){
                        this.message.setText("Tous les champs doivent etre remplis !");

                    }else {
                        //on essaie de se connecter
                        res = GetJoueur.LogIn(this.nameText.getText(), this.MdpText.getText());
                        System.out.println(res);

                        if (res.equals("echoue")) {
                            this.message.setText("Une erreur s'est produit !");

                        } else if (res.equals("pas d'internet")) {
                            this.message.setText("Verifiez votre connection internet !");

                        } else if (res.equals("no user found")) {
                            this.message.setText("Email ou mot de passe inconnu !");

                        } else {
                            // on enregistre le joueur qui est maintenant connecté
                            game.isLoggedin = true;
                            game.pseudo = res;
                            // on passe à l'écran suivant
                            game.setScreen(game.menuEtTableau[1]);
                        }
                    }
                }
            }
        }
    }

        @Override
        public void resize(int width, int height) {
            viewport.update(width, height);
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {
            stageLogin.dispose();

        }
}
