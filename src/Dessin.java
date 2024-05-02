/**
 * Dessin.java
 *
 * Created: Sun Sep 26 13:02:15 1999
 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.Vector;


/**
 * Defini le contenu de la fenêtre de l'application d'animation des visages. Une zone
 * de dessin est un Canvas qui gère un liste d'objets VisageRond. Lorsqu'il se réaffiche
 * l'objet Dessin redessinne les différents objets VisageRond contenu dans cette liste.
 *
 * @author Philippe Genoud
 * @version
 */
public class Dessin extends Canvas {
    
    private Dimension offDimension;
    private Image offImage;
    private Graphics offGraphics = null;
    
    /**
     * stocke la liste des Visages devant ayant été ajoutés à cette zone de
     * dessin.
     */
    private Vector listeDesVisages = new Vector();
    
    /**
     * retourne la largeur de la zone de dessin.
     * @return la largeur.
     */
    public int getLargeur() {
        return getWidth();
    }
    
    /**
     * retourne la hauteur de la zone de dessin.
     * @return la hauteur.
     */
    public int getHauteur() {
        return getHeight();
    }
    
    /**
     * ajoute un VisageRond à la zone de dessin.
     * @param v le visage à ajouter au Dessin
     * @see VisageRond
     */
    public void ajouterObjet(VisageRond v) {
        
        if (! listeDesVisages.contains(v)) {
            // l'objet n'est pas déjà dans la liste
            // on le rajoute a la liste des objets du dessin
            listeDesVisages.add(v);
            v.setDessin(this);
            // le dessin se réaffiche
            repaint();
        }
    }
    
    /**
     * temporisation de l'animation.
     * @param durée delai de temporisation en ms.
     */
    public void pause(int durée) {
        try {
            Thread.currentThread().sleep(durée);
        } catch (Exception e) {}
    }
    
    /**
     * rafraichissement de la zone de dessin. Lorsque cette méthode est
     * invoquée, la zone de dessin se réaffiche.
     * @param g le contexte graphique
     */
    public void update(Graphics g) {
        Dimension d = getSize();
        if ((offGraphics == null) || (d.width != offDimension.width)
        || (d.height != offDimension.height)) {
            offDimension = d;
            offImage = createImage(d.width, d.height);
            offGraphics = offImage.getGraphics();
        }
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        offGraphics.setColor(getForeground());
        dessinerLesVisages(offGraphics);
        g.drawImage(offImage, 0, 0, this);
    }
    
    /**
     * Parcourt la liste des Visages pour afficher chacun d'eux.
     * @param g le contexte graphique
     */
    private void dessinerLesVisages(Graphics g) {
        Iterator lesObjets = listeDesVisages.iterator();
        
        while (lesObjets.hasNext()) {
            VisageRond objCour = (VisageRond)(lesObjets.next());
            objCour.dessiner(g);
        }
    }
    
} // Dessin
