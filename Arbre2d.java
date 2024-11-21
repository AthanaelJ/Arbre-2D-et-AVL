import java.lang.Math;
import java.awt.Color;
/**
 *Classe Arbre2d.
 *ici les Arbres2d n'alternent pas entre les sens de découpe x et y : il peut y avoir k découpes en x de suite
 *Caractérisée par :
 *un booléen qui indique si l'arbre est une feuille (true) ou une branche (false)
 *une couleur (mise à rose si c'est une branche)
 *un booléen qui indique l'axe de découpe en x (true) ou en y (false)
 *un arbre gauche et droit
 *une valeur de découpe
 */
public class Arbre2d{
	//Attribut
	private boolean feuille;
  private Color couleur;
	private boolean decoupe; //true=x ; false=y
	private Arbre2d G;
	private Arbre2d D;
	private int val;
	//Méthode

  /**
   *Constructeur Arbre2d : construit une branche ; met feuille à false ; et assigne la couleur rose comme couleur d'erreur.
   *
   *@param decoupe un booléen qui détermine l'axe de la découpe (true=x et false=y)
   *@param G le sous arbre de gauche
   *@param D le sous arbre de droite
   *@param xouy un entier qui correspond à la valeur de la découpe
   */
	public Arbre2d(boolean decoupe, Arbre2d G, Arbre2d D, int xouy){
		feuille=false;
		this.couleur=Color.PINK;//couleur d'erreur
		this.decoupe=decoupe;
		this.G=G;
		this.D=D;
		val=xouy;
	}

  /**
   *Constructeur Arbre2d : construit une feuille ; met feuille à true ; pointe sur lui-même pour les sous arbres de droite et de gauche ; met en découpe true (arbitrairement) ; et val à 0.
   *
   *@param couleur la couleur de la feuille
   */
	public Arbre2d(Color couleur){
		feuille=true;
		this.couleur=couleur;
		decoupe=true;
		G=this;
		D=this;
		val=0;
	}

  /**
   *getter retournant un booléen indiquant le sens de la découpe (true=x et false=y)
   *
   *@return true si la découpe est sur x, false si la découpe est sur y
   */
	public boolean getDecoupe(){
		return decoupe;
	}

  /**
   *getter retournant un booléen indiquant s'il s'agit d'une feuille
   *
   *@return true si c'est une feuille, false si c'est une branche
   */
	public boolean estF(){
		return feuille;
	}

  /**
   *getter retournant un entier correspndant à la valeur de découpe
   *
   *@return un entier
   */
	public int getVal(){
		return val;
	}

  /**
   *getter retournant la couleur de l'arbre
   *
   *@return une couleur
   */
	public Color getCouleur(){
		return couleur;
	}

  /**
   *getter retournant le sous-arbre de gauche
   *
   *@return un Arbre2d
   */
	public Arbre2d getG(){
		return G;
	}

  /**
   *getter retournant le sous-arbre de droite
   *
   *@return un Arbre2d
   */
	public Arbre2d getD(){
		return D;
	}

  /**
   *setter affectant le sous-arbre de gauche
   *
   *@param a un Arbre2d
   */
	public void setG(Arbre2d a){
		G=a;
	}

  /**
   *setter affectant le sous-arbre de droite
   *
   *@param a un Arbre2d
   */
	public void setD(Arbre2d a){
		D=a;
	}






  /**
   *Fonction calculant le poids d'une feuille
   *
   *@param minX la coordonnée x inférieure de la feuille
   *@param maxX la coordonnée x supérieure de la feuille
   *@param minY la coordonnée y inférieure de la feuille
   *@param maxY la coordonnée y supérieure de la feuille
   *
   *@return le poids de la feuille
   */
	public double poidsFeuille(int minX, int maxX, int minY, int maxY){
		int w=maxX-minX;
		int h=maxY-minY;
		return (w*h)/Math.pow(w+h,1.5);
	}

  /**
   *Procédure choisissant et effectuant une découpe aléatoire
   *
   *@param p les paramètres de la toile
   *@param minX la coordonnée x inférieure de la feuille
   *@param maxX la coordonnée x supérieure de la feuille
   *@param minY la coordonnée y inférieure de la feuille
   *@param maxY la coordonnée y supérieure de la feuille
   *
   *@throws Exception si la feuille ne peut plus être découpé selon les critères renseignés en paramètre (p)
   */
	public void choisirDivision(Param p, int minX, int maxX, int minY, int maxY) throws Exception {
		double w=maxX-minX;
		double h=maxY-minY;
		double random;
		boolean decoupe;
		double PCoupe=p.getProportionCoupe();
		int val;
		if((w<p.getMinDimensionCoupe())||(h<p.getMinDimensionCoupe())){
			throw new Exception("la plus grosse feuille ne peut plus être découpée, fin de la génération");
		}else{
			random=p.randomDouble();//tire une valeur qui sera soit utilisé pour le choix de l'axe découpe 
			if(random<=(w/(w+h))){
				decoupe=true;
			}else{
				decoupe=false;
			}
		}
		random=p.randomDouble();//tire un second nombre pour choisir l'endroit où on coupe
		if(decoupe){
			double floor=Math.floor(w*PCoupe);
			double ceil=Math.ceil(w*(1-PCoupe));
			val=(int)(random*(ceil-floor)+floor+minX);
		}else{
			double floor=Math.floor(h*PCoupe);
			double ceil=Math.ceil(h*(1-PCoupe));
			val=(int)(random*(ceil-floor)+floor+minY);
		}
		change(decoupe, val);
	}

  /**
   *Procédure qui permet de changer une feuille en branche
   *
   *@param decoupe l'axe de découpe
   *@param v la valeur de découpe
   */
	private void change(boolean decoupe, int v){
		feuille=false;
		this.couleur=Color.PINK;//couleur d'erreur
		this.decoupe=decoupe;
		val=v;
	}
  
}