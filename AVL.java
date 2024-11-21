/**
 *Classe AVL.
 *Caractérisée par :
 *une balance
 *un booléen indicant si l'arbre est vide
 *une structure avec un arbre, quatre entiers et un réel
 *un sous-arbre gauche et droit
 */
public class AVL{
	//Attributs
	private int balance;
	private boolean vide;
  private ArbreIntIntIntIntDouble elt;
	private AVL G;
	private AVL D;
	//Méthodes

	/**
	 *Constructeur AVL par défaut, crée un arbre vide
	 *
	 */
	public AVL(){
		balance=0;
		vide=true;
		elt=new ArbreIntIntIntIntDouble();
		G=this;
		D=this;
	}

	/**
	 *Constructeur AVL
	 *
	 *@param elt l'élement du noeud
	 *@param G le sous arbre de gauche
	 *@param D le sous arbre de droite
	 */
  public AVL(ArbreIntIntIntIntDouble elt, AVL G, AVL D){
		vide=false;
		balance=D.hauteur()-G.hauteur();
		this.elt=elt;
		this.G=G;
		this.D=D;
		if ((balance>1)||(balance<-1)){
			AVL b=equilibrer();
			this.elt=b.getElt();
			this.G=b.getG();
			this.D=b.getD();
		}
	}

	/**
   *getter retournant true si l'arbre est vide, false sinon
   *
   *@return true si l'arbre est vide false sinon.
   */
	public boolean estVide(){
		return vide;
	}

	/**
   *getter retournant la balance
   *
   *@return la balance.
   */
	public int getBal(){
		return balance;
	}

	/**
   *setter de la balance
   *
   *@param b la nouvelle balance.
   */
	public void setBalance(int b){
		balance=b;
	}

	/**
   *getter retournant l'élément 
   *
   *@return l'élément.
   */
	public ArbreIntIntIntIntDouble getElt(){
		return elt;
	}

	/**
   *getter retournant le sous arbre de gauche
   *
   *@return le sous arbre de gauche.
   */
	public AVL getG(){
		return G;
	}

	/**
   *getter retournant le sous arbre de droite
   *
   *@return le sous arbre de droite.
   */
	public AVL getD(){
		return D;
	}

	/**
   *setter du sous arbre de gauche
   *
   *@param a le nouveau sous arbre de gauche.
   */
	public void setG(AVL a){
		G=a;
	}

	/**
   *setter du sous arbre de droite
   *
   *@param a le nouveau sous arbre de droite.
   */
	public void setD(AVL a){
		D=a;
	}

	/**
   *fonction calculant la hauteur du noeud
   *
   *@return la hauteur.
   */
	public int hauteur(){
		return hauteur(1);
	}

  /**
   *fonction privé calculant la hauteur du noeud
   *
	 *@param la profondeur de la descente 
	 *
   *@return la hauteur.
   */
	private int hauteur(int h){
		if(vide){
			return h-1;//return la hauteur du noeud précédent
		}
    if(G.estVide()){
      return D.hauteur(h+1);
    }
    if(D.estVide()){
      return G.hauteur(h+1);
    }
		return maximum(G.hauteur(h+1), D.hauteur(h+1));
	}

	/**
   *fonction privé retourant le maximum de deux valeur
   *
	 *@param a un entier
	 *@param b un entier
	 *
   *@return le maximum.
   */
	private int maximum(int a, int b){
		if(a>b){
			return a;
		}
		return b;
	}


  /**
   *fonction privé retourant le minimum de deux valeur
   *
	 *@param a un entier
	 *@param b un entier
	 *
   *@return le minimum.
   */
	private int minimum(int a, int b){
		if(a<b){
			return a;
		}
		return b;
	}


  /**
   *Fonction retournant le plus grand élément de l'AVL
   *
   *@return ArbreIntIntIntIntDouble l'élément maximal de l'AVL
   */
	public ArbreIntIntIntIntDouble max(){
		if(D.estVide()){
			return elt;
		}
		return D.max();
	}


  /**
   *Fonction retournant le plus petit élément de l'AVL
   *
   *@return ArbreIntIntIntIntDouble l'élément minimal de l'AVL
   */
	private ArbreIntIntIntIntDouble min(){
		if(G.estVide()){
			return elt;
		}
		return G.min();
	}


  /**
   *Fonction équilibrant l'AVL
   *
   *@return AVL équilibré
   */
	public AVL equilibrer(){
		if(balance>=2){
			if (D.getBal()>=0){
				return ROTG();
			}else{
				D=D.ROTD();
				return ROTG();
			}
		}else if (balance<=-2){
			if (G.getBal()<=0){
				return ROTD();
			}else{
				G=G.ROTG();
				return ROTD();
			}
		}
		return this;
	}


  /**
   *Fonction effectuant une rotation à gauche
   *
   *@return AVL modifié après une rotation
   */
	public AVL ROTG(){
		AVL B;
		int a, b;
		B=D;
		a=balance;
		b=B.getBal();
		D=B.getG();
		if(vide){
			B.setG(new AVL());
		}else{
			B.setG(this);
		}
		balance=a-maximum(b,0)-1;
		B.setBalance(minimum(b-1, minimum(a-2, a+b-2)));
		return B;
	}


  /**
   *Fonction effectuant une rotation à droite
   *
   *@return AVL modifié après une rotation
   */
	public AVL ROTD(){
		AVL B;
		int a, b;
		B=G;
		a=balance;
		b=B.getBal();
		G=B.getD();
		if(vide){
			B.setD(new AVL());
		}else{
			B.setD(this);
		}
		balance=a-minimum(b,0)+1;
		B.setBalance(maximum(b+1, maximum(a+2, a+b+2)));
		return B;
	}


  /**
   *Fonction ajoutant un élément dans l'AVL
   *
   *@param e l'élément à ajouter
   *
   *@return AVL l'AVL modifié
   */
	public AVL add(ArbreIntIntIntIntDouble e){
		boolean stock=true;
		AVLInt temp=new AVLInt(this, 0);
		while (stock) {
			stock=false;
			try {
				temp=ajouterRec(e);//On essaye d'ajouter
			}
			catch (Exception exception){//Si la valeur est déjà dans l'AVL alors on l'augmente légèrement et on réessaye (pire cas quand toutes le valeurs sont les mêmes mais très peu probable car toutes les valeurs sont sensé être aléatoire)
				e.setDouble(e.getDouble()-0.000001);
				stock=true;
			}
		}
		return temp.getAVL();
	}


  
	private AVLInt ajouterRec(ArbreIntIntIntIntDouble e) throws Exception {
		if(vide){
			return new AVLInt(new AVL(e, new AVL(), new AVL()), 1);
		}
		if(e.getDouble()==elt.getDouble()){
			throw new Exception("deux valeurs égales");
		}
		AVLInt stock;
		int h;
		if(e.getDouble()>elt.getDouble()){
			stock=D.ajouterRec(e);
			D=stock.getAVL();
			h=stock.getVar();
		}else{
			stock=G.ajouterRec(e);
			G=stock.getAVL();
			h=0-stock.getVar();
		}
		if(h==0){
			return new AVLInt(this, 0);
		}else{
			balance+=h;
			AVL B=equilibrer();
			if(B.getBal()==0){
				return new AVLInt(B, 0);
			}else{
				return new AVLInt(B, 1);
			}
		}
	}


  /**
   *Fonction supprimant un élément dans l'AVL
   *
   *@param e un ArbreIntIntIntIntDouble
   *
   *@return AVL
   */
	public AVL supp(ArbreIntIntIntIntDouble e){
		AVLInt temp=enlever(e);
		return temp.getAVL();
	}


  /**
   *Fonction privée supprimant un élément dans l'AVLInt
   *
   *@param e un ArbreIntIntIntIntDouble
   *
   *@return AVLInt
   */
	private AVLInt enlever(ArbreIntIntIntIntDouble e){
		if(vide){
			return new AVLInt(this, 0);
		}
		AVLInt stock;
		int h;
		if(e.getDouble()>elt.getDouble()){
			stock=D.enlever(e);
			D=stock.getAVL();
			h=stock.getVar();
		}else if(e.getDouble()<elt.getDouble()){
			stock=G.enlever(e);
			G=stock.getAVL();
			h=0-stock.getVar();
		}else if(G.estVide()){
			return new AVLInt(D, -1);
		}else if(D.estVide()){
			return new AVLInt(G, -1);
		}else{
			elt=D.min();
			stock=D.supprMin();
			D=stock.getAVL();
			h=stock.getVar();
		}
		if(h==0){
			return new AVLInt(this, 0);
		}else{
			balance+=h;
			AVL B=equilibrer();
			if(B.getBal()==0){
				return new AVLInt(B, -1);
			}else{
				return new AVLInt(B, 0);
			}
		}
	}

  /**
   *Fonction privée supprimant l'élément minimum de l'AVLInt
   *
   *@return AVLInt
   */
	private AVLInt supprMin(){
		if(G.estVide()){
			return new AVLInt(D, -1);
		}
		AVLInt stock;
		int h;
		stock=G.supprMin();
		G=stock.getAVL();
		h=0-stock.getVar();
		if(h==0){
			return new AVLInt(this, 0);
		}else{
			balance+=h;
			AVL B=equilibrer();
			if(balance==0){
				return new AVLInt(B, -1);
			}else{
				return new AVLInt(B, 0);
			}
		}
	}
}
