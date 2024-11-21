import java.awt.Color;
/**
 *Classe Strategy.
 *Caractérisée par :
 *une liste de paramètre
 *un AVL qui sert de tableau
 *un Arbre2d
 *une Image
 */
public class Strategy{
	//Attributs
	private Param p;
	private AVL tab;
	private Arbre2d a;
	private Image img;

  
	//Méthodes
	
  /**
   *Constructeur de Strategy.
	 *
   *@param p une structure contenant les paramètres 
   */
  public Strategy(Param p){
		this.p=p;
		tab=new AVL();
		a=new Arbre2d(Color.WHITE);
		img=new Image(p.getLargeur(), p.getHauteur());
	}

  /**
   *Procédure générant un tableau à partir des paramètres
   */
	public void genereArbreAleatoire(){ 
		try {
			genereArbreAleatoirePrive();
		}
		catch (Exception e){
			//fin de la génération car feuille indivisible
		}
	}

	/**
	 *Procédure privé générant un tableau à partir des paramètres
	 *
	 *@throws Exception si la feuille ne peut plus être découpé selon les critères renseignés en paramètre (p)
   */
	private void genereArbreAleatoirePrive() throws Exception {
		int mx, MX, my, MY, v;
		Arbre2d arbre;
		ArbreIntIntIntIntDouble stock;
		ArbreIntIntIntIntDouble stock1;
		ArbreIntIntIntIntDouble stock2;
		Color couleur;
		double poids=a.poidsFeuille(0, p.getLargeur(), 0, p.getHauteur());//on initialise notre toile et on stoque notre première feuille dans notre tableau
		stock=new ArbreIntIntIntIntDouble(a, 0, p.getLargeur(), 0, p.getHauteur(), poids);
		tab=tab.add(stock);
		for(int i=1; i<p.getNbFeuilles(); i++){//Les conditions d'arrêt sont soit on fait le nombre de feuille voulu soit on ne peut plus couper la feuille la plus lourde
			if ((i%p.getFrec()==0)&&(p.getSGR())){//Si on est dans la stratégie des gros rectangle alors un rectangle est choisi et ne sera plus divisé
        stock=choisirFeuille();
				stock.setDouble(0.00001);
				tab=tab.add(stock);
			}else{
        stock=choisirFeuille();
				arbre=stock.getArbre();
				couleur=arbre.getCouleur();
				try {
					arbre.choisirDivision(p, stock.getInt1(), stock.getInt2(), stock.getInt3(), stock.getInt4());
				}
				catch (Exception e){
					tab=tab.add(stock);//on réajoute a l'AVL la feuille car elle ne peux plus être divisé
					throw e;//on met fin a la découpe de l'arbre
				}
				arbre.setG(new Arbre2d(choisirCouleur(couleur)));
				arbre.setD(new Arbre2d(choisirCouleur(couleur)));
				mx=stock.getInt1();
				MX=stock.getInt2();
				my=stock.getInt3();
				MY=stock.getInt4();
				v=arbre.getVal();
				if(arbre.getDecoupe()){
					poids=arbre.getG().poidsFeuille(mx, v, my, MY);
					stock1=new ArbreIntIntIntIntDouble(arbre.getG(), mx, v, my, MY, poids);
					poids=arbre.getD().poidsFeuille(v, MX, my, MY);
					stock2=new ArbreIntIntIntIntDouble(arbre.getD(), v, MX, my, MY, poids);
				}else{				
					poids=arbre.getG().poidsFeuille(mx, MX, my, v);
					stock1=new ArbreIntIntIntIntDouble(arbre.getG(), mx, MX, my, v, poids);
					poids=arbre.getD().poidsFeuille(mx, MX, v, MY);
					stock2=new ArbreIntIntIntIntDouble(arbre.getD(), mx, MX, v, MY, poids);
				}
				tab=tab.add(stock1);
				tab=tab.add(stock2);        
			}
		}
	}

  /**
   *Retourne la plus grosse feuille de l'arbre
	 *
	 *@return la plus grosse feuille
	 */
  private ArbreIntIntIntIntDouble choisirFeuille() {
    ArbreIntIntIntIntDouble stock=tab.max();
		tab=tab.supp(stock);
    return stock;
  }

	/**
   *Créer une image à partir de la toile stockée dans Arbre2d
	 *
	 *@param s le nom de l'image créée
	 */
	public void creerImage(String s){
		ArbreIntIntIntIntDouble stock;
		img.setRectangle(0, p.getLargeur(), 0, p.getHauteur(), Color.MAGENTA);//pour voir les problèlmes on met du magenta
		do{
			stock=tab.max();
			img.setRectangle(stock.getInt1(), stock.getInt2(), stock.getInt3(), stock.getInt4(), stock.getArbre().getCouleur());
			tab=tab.supp(stock);
		}while(!(tab.estVide()));
		
		colorierBranche(a, 0, p.getLargeur(), 0, p.getHauteur(), p.getLargeurLigne(),  p.getLargeur(), p.getHauteur());
		try{
			img.save(s+".png");
		}
		catch (Exception e){
			System.out.println("Image impossible à sauvgarder");
		}
	}

	/**
   *Colorie les traits gris entre les rectangles
	 *
	 *@param arb l'arbre qui contient la toile
	 *@param startX la coordonnée minimale X de la sous-fenêtre où l'on travaille
	 *@param endX la coordonnée maximale X de la sous-fenêtre où l'on travaille
	 *@param startY la coordonnée minimale Y de la sous-fenêtre où l'on travaille
	 *@param endY la coordonnée maximale Y de la sous-fenêtre où l'on travaille
	 *@param largeur du traits gris
	 *@param maxXabs la valeur maximale X de la toile entière
	 *@param maxYabs la valeur maximale Y de la toile entière
	 */
	private void colorierBranche(Arbre2d arb, int startX, int endX, int startY, int endY, int largeur, int maxXabs, int maxYabs){
		if(!(arb.estF())&&(arb.getVal()>0)){
			int val=arb.getVal();
			if(arb.getDecoupe()){
				int epaisG=fermeture(val, largeur, false, 0);
				int epaisD=fermeture(val, largeur, true, maxXabs);
				img.setRectangle(epaisG, epaisD, startY, endY, Color.GRAY);
				colorierBranche(arb.getG(), startX, val, startY, endY, largeur, maxXabs, maxYabs);
				colorierBranche(arb.getD(), val, endX, startY, endY, largeur, maxXabs, maxYabs);
			}else{
				int epaisG=fermeture(val, largeur, false, 0);
				int epaisD=fermeture(val, largeur, true, maxYabs);
				img.setRectangle(startX, endX, epaisG, epaisD, Color.GRAY);
				colorierBranche(arb.getG(), startX, endX, startY, val, largeur, maxXabs, maxYabs);
				colorierBranche(arb.getD(), startX, endX, val, endY, largeur, maxXabs, maxYabs);
			}
		}
	}

	/**
   *Créer une image à partir de la toile stockée dans Arbre2d avec la stratégie 2
	 *
	 *@param s le nom de l'image créée
	 */
	public void creerImageS2(String s){
		img.setRectangle(0, p.getLargeur(), 0, p.getHauteur(), new Color(230, 230, 230));
		
		colorierBrancheS2(a, 0, p.getLargeur(), 0, p.getHauteur(), p.getLargeurLigne());
		try{
			img.save(s+".png");
		}
		catch (Exception e){
			System.out.println("Image impossible à sauvgarder");
		}
	}




  /**
   *Procédure permettant de colorier les branches lors de l'exécution de la 2e stratégie
   *
   *@param arb l'arbre qui contient la toile
	 *@param startX la coordonnée minimale X de la sous-fenêtre où l'on travaille
	 *@param endX la coordonnée maximale X de la sous-fenêtre où l'on travaille
	 *@param startY la coordonnée minimale Y de la sous-fenêtre où l'on travaille
	 *@param endY la coordonnée maximale Y de la sous-fenêtre où l'on travaille
   *@param largeur du traits gris
   */
	private void colorierBrancheS2(Arbre2d arb, int startX, int endX, int startY, int endY, int largeur){
		if(!(arb.estF())&&(arb.getVal()>0)){
			int val=arb.getVal();
			Color col=choisirCouleurS2();
			if(arb.getDecoupe()){
				int epaisG=fermeture(val, largeur, false, 0);
				int epaisD=fermeture(val, largeur, true, endX);
				img.setRectangle(epaisG, epaisD, startY, endY, col);
				colorierBrancheS2(arb.getG(), startX, endX, startY, endY, largeur);
				colorierBrancheS2(arb.getD(), startX, endX, startY, endY, largeur);
			}else{
				int epaisG=fermeture(val, largeur, false, 0);
				int epaisD=fermeture(val, largeur, true, endY);
				img.setRectangle(startX, endX, epaisG, epaisD, col);
				colorierBrancheS2(arb.getG(), startX, endX, startY, endY, largeur);
				colorierBrancheS2(arb.getD(), startX, endX, startY, endY, largeur);
			}
		}
	}


  /**
   *Fonction privée réduisant la valeur des coordonnées d'une feuille si elle devait dépasser le cadre de la toile
   *
   *@param val la valeur à vérifier
   *@param l la largeur du trait gris
   *@param signe booléen déterminant si il s'agit d'une bordure à gauche (false) ou à droite (true)
   *@param bord la coordonnée de la bordure à ne pas dépasser
   *
   *@return un entier
   */
	private int fermeture(int val, int l, boolean signe, int bord){
		int res;
		if (signe) {
			res=val+l;
			if(res>bord){
				res=bord;
			}
		}else{
			res=val-l;
			if(res<bord){
				res=bord;
			}
		}
		return res;
	}


  /**
   *Fonction retournant la couleure choisie pour le tracage lors de la 1ère stratégie
   *
   *param couleur la couleur de la case précédente
   *
   *@return une couleur
   */
	public Color choisirCouleur(Color couleur){
		double random=p.randomDouble();
		Color stock;
		double probMeme=p.getMemeCouleurProb();
		if(random<=probMeme){
			stock=couleur;
		}else if(random<=probMeme+((1-probMeme)/5)){
			stock=Color.RED;
		}else if(random<=probMeme+((1-probMeme)/5)*2){
			stock=Color.BLUE;
		}else if(random<=probMeme+((1-probMeme)/5)*3){
			stock=Color.YELLOW;
		}else if(random<=probMeme+((1-probMeme)/5)*4){
			stock=Color.BLACK;
		}else{
			stock=Color.WHITE;
		}
		return stock;
	}


  /**
   *Fonction retournant la couleure choisie pour le tracage lors de la 2e stratégie
   *
   *@return une couleur
   */
	public Color choisirCouleurS2(){
		double random=p.randomDouble();
		Color stock;
		if(random<=0.33){
			stock=Color.RED;
		}else if(random<=0.66){
			stock=Color.BLUE;
		}else{
			stock=Color.YELLOW;
		}
		return stock;
	}
	
}