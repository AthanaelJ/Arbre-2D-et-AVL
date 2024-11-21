commande de compilation et éxécution : <br />
javac -g Main.java <br />
<br />
<br />
java Main l h nbFeuilles minDimensionCoupe proportionCoupe memeCouleurProb largeurLigne <br />
ou <br />
java Main l h nbFeuilles minDimensionCoupe proportionCoupe memeCouleurProb largeurLigne seed strategy2? strategyGrosRectangule? frequancie name <br />
<br />
<br />
où les paramètres sont :<br />
l ∈ ℕ*<br />
h ∈ ℕ*<br />
nbFeuilles ∈ ℕ*<br />
minDimensionCoupe ∈ ℕ*<br />
proportionCoupe ∈ [0, 0.5[<br />
memeCouleurProb ∈ [0, 1]<br />
largeurLigne ∈ [0, min(⌊l/2⌋, ⌊h/2⌋)[<br />
(optionel) seed ∈ ℝ ou "n" si on ne veut pas de seed<br />
(optionel) Strategy 2 ? (y/n)<br />
(optionel) Strategy "gros rectangles" ? (y/n)<br />
(optionel) la fréquance des "gros rectangles" ∈ ℕ* (10 si pas spécifié)<br />
(optionel) name le nom de l'image généré