
/**
 *
 * AppliVisage1.java
 *
 *
 * Created: Sun Sep 26 13:41:42 1999
 */

import java.awt.Frame;


/**
 * Animation d'un visage dans une fen�tre graphique.
 * Un visage est dessin� � l'int�rieur d'une fen�tre et se d�place. Chaque fois
 * que l'un des bords est atteint, le visage change de direction.
 * @author Philippe Genoud
 * @version
 */

public class AppliVisage1  {
    
    public static void main(String[] args) {
        
        // la fen�tre graphique
        Frame laFenetre = new Frame("VISAGE ANIME");
        laFenetre.setSize(512,512);
        
        // cr�� la zone de dessin et la place dans la fen�tre
        Dessin d = new Dessin();
        laFenetre.add(d);
        
        
        // creation d'un objet VisageRond
        VisageRond  v1 = new VisageRond();
        
        // on rajoute cet objet la z�ne de dessin
        d.ajouterObjet(v1);
        
        //  affiche la fen�tre
        laFenetre.show();
        
        // la boucle d'animation
        // c'est une boucle infinie, le programme devra �tre interrompu
        // par CTRL-C
        while (true) {
            // le visage a atteint un des bords, il change de direction
            if (v1.bordAtteint())
                v1.inverserDxEtDy();
            
            // le visage effectue un d�placement �l�mentaire
            v1.deplacer();
            
            // la zone de dessin se r�affiche
            d.repaint();
            
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            
        }
    }
    
} // AppliVisage1

