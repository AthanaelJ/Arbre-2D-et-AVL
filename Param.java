import java.util.Random;
/**
 *Classe Param.
 *stock les paramètres souhaités par l'utilisateur pour la création de la toile
 *Caractérisée par :
 *la largeur et la hauteur de la toile
 *le nombre de rectangles de la toile
 *la taille (longueur ou hauteur) minimum d'un rectangle de la toile
 *le pourcentage que l'on s'autorise à découper
 *la probabilité d'avoir la même couleur en créant de nouveaux rectangles
 *la demi-largeur des lignes grise
 *un générateur d'aléatoire géré avec une seed soit renseignée par l'utilisateur, soit générée et annoncée aléatoirement
 *un booléen qui indique si l'on se trouve dans la stratégie des "gros rectangles"
 *la fréquence de génération des gros rectangles
 */
public class Param {
	private int largeur;
	private int hauteur;
	private int nbFeuilles;
	private int minDimensionCoupe;
	private double proportionCoupe;
	private double memeCouleurProb;
	private int largeurLigne;
	private Random generateur;
	private boolean stratGR;
	private int frec;

	/**
   *Constructeur Param sans seed.
   *
   *@param l longeur de la toile
   *@param h hauteur de la toile
   *@param nbFeuilles nombre de rectangles sur la toile
   *@param minDimensionCoupe la taille minimum d'un rectangle
   *@param proportionCoupe le pourcentage que l'on s'autorise à découper
   *@param memeCouleurProb la probabilité d'avoir la même couleur
   *@param largeurLigne la demi-largeur des ligne grise
   */
	public Param(int l, int h, int nbFeuilles, int minDimensionCoupe, double proportionCoupe, double memeCouleurProb, int largeurLigne){
		largeur=l;
		hauteur=h;
		this.nbFeuilles=nbFeuilles;
		this.minDimensionCoupe=minDimensionCoupe;
		this.proportionCoupe=proportionCoupe;
		this.memeCouleurProb=memeCouleurProb;
		this.largeurLigne=largeurLigne;
		Random seedGenerateur=new Random();
		long seed=(long)seedGenerateur.nextInt();
		System.out.println("la seed est : "+seed);
		generateur=new Random(seed);
		stratGR=false;
		frec=-1;
	}
  
	/**
   *Constructeur Param avec seed.
	 *
   *@param l longeur de la toile
   *@param h hauteur de la toile
   *@param nbFeuilles nombre de rectangles sur la toile
   *@param minDimensionCoupe la taille minimum d'un rectangle
   *@param proportionCoupe le pourcentage que l'on s'autorise à découper
   *@param memeCouleurProb la probabilité d'avoir la même couleur
   *@param largeurLigne la demi-largeur des lignes grises
	 *@param seed la seed de la toile
	 *@param SGR si la stratégie "gros rectangles" est utilisée : true
	 *@param frec la fréquence des gros rectangles
   */
	public Param(int l, int h, int nbFeuilles, int minDimensionCoupe, double proportionCoupe, double memeCouleurProb, int largeurLigne, long seed, boolean SGR, int frec){
		largeur=l;
		hauteur=h;
		this.nbFeuilles=nbFeuilles;
		this.minDimensionCoupe=minDimensionCoupe;
		this.proportionCoupe=proportionCoupe;
		this.memeCouleurProb=memeCouleurProb;
		this.largeurLigne=largeurLigne;
		System.out.println("la seed est : "+seed);
		generateur=new Random(seed);
		stratGR=SGR;
		this.frec=frec;
	}

	/**
   *getter retournant un booléen indiquant si la stratégie "gros rectangles" est utilisée (true).
   *
   *@return vrai si la stratégie "gros rectangles" est utilisée, false sinon.
   */
	public boolean getSGR(){
		return stratGR;
	}
	
	/**
   *getter retournant la fréquence des gros rectangles
   *
   *@return la fréquence.
   */
	public int getFrec(){
		return frec;
	}

	/**
   *getter retournant un entier naturel aléatoire entre 0 incluse et la valeur en paramètre excluse.
   *
	 *@param maxExclu la valeur maximale excluse
	 *
   *@return un entier ∈ [0, maxExclu[.
   */
	public int randomInt(int maxExclu){
		return generateur.nextInt(maxExclu);
	}

	/**
   *getter retournant un réel aléatoire entre 0 et 1.
   *
   *@return un réel ∈ [0, 1].
   */
	public double randomDouble(){
		return generateur.nextDouble();
	}

	/**
   *getter retournant la largeur de la toile.
   *
   *@return la largeur.
   */
	public int getLargeur(){
		return largeur;
	}
	
	/**
   *getter retournant la hauteur de la toile.
   *
   *@return la hauteur.
   */
	public int getHauteur(){
		return hauteur;
	}
	
	/**
   *getter retournant le nombre de rectangles de la toile.
   *
   *@return le nombre de rectangles.
   */
	public int getNbFeuilles(){
		return nbFeuilles;
	}
	
	/**
   *getter retournant la dimension minimale de coupe.
   *
   *@return la dimension minimale de coupe.
   */
	public int getMinDimensionCoupe(){
		return minDimensionCoupe;
	}
	
	/**
   *getter retournant la proportion de coupe.
   *
   *@return la proportion de coupe.
   */
	public double getProportionCoupe(){
		return proportionCoupe;
	}
	
	/**
   *getter retournant probabilité d'avoir la même couleur.
   *
   *@return la probabilité d'avoir la même couleur.
   */
	public double getMemeCouleurProb(){
		return memeCouleurProb;
	}
	
	/**
   *getter retournant la demi-largeur des lignes grises.
   *
   *@return la demi-largeur des lignes grises.
   */
	public int getLargeurLigne(){
		return largeurLigne;
	}
	
}
	