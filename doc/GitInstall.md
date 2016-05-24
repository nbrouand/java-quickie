# Installation
## Cygwin
- Installer dans Cygwin les paquets suivants :
	- git
	- git-completion
	- antiword
	- poppler
- Installer KDiff3
- Ajouter C:\cygwin\bin à votre variable d'environnement PATH

## Git for Windows
Télécharger et installer Git for Windows.

## Linux
Installer git et KDiff3 et antiword via votre gestionnaire de paquets.

# Configuration
## Git
Configuration de base

```sh
# Alias utiles
git config --global alias.git "! git"
git config --global alias.st "status"
git config --global alias.ci "commit"
git config --global alias.co "checkout"
git config --global alias.glog "log --graph --oneline --decorate --branches --tags --date-order --full-history"
git config --global alias.lg "log --graph --decorate --date-order --full-history --pretty=format:'%C(yellow)%h%Creset <%C(red)%an%Creset> (%C(green)%ai%Creset)%C(auto)%d%Creset %s'"
git config --global alias.lga "log --graph --decorate --date-order --full-history --pretty=format:'%C(yellow)%h%Creset <%C(red)%an%Creset> (%C(green)%ai%Creset)%C(auto)%d%Creset %s' --all"
git config --global alias.incoming "! git fetch --quiet && git log --graph --oneline --date-order --full-history ..@{upstream}"
git config --global alias.outgoing "! git fetch --quiet && git log --graph --oneline --date-order --full-history @{push}.."
git config --global alias.incoming-all "! git fetch --quiet && git for-each-ref --format='%(refname:short)' refs/heads | xargs -I {} git log --graph --oneline --decorate --date-order --full-history {}..{}@{upstream}"
git config --global alias.outgoing-all "! git fetch --quiet && git for-each-ref --format='%(refname:short)' refs/heads | xargs -I {} git log --graph --oneline --decorate --date-order --full-history {}@{push}..{}"
git config --global alias.forget "update-index --assume-unchanged"
git config --global alias.unforget "update-index --no-assume-unchanged"
git config --global alias.forgotten "! git ls-files -v | grep ^[a-z]"
git config --global alias.vimdiff "difftool --no-prompt --tool=vimdiff"
# Couleur
git config --global color.ui "auto"
# Pull en mode '--rebase --preserve'
git config --global pull.rebase "preserve"
git config --global rebase.autostash "true"
git config --global rebase.autosquash "true"
# Push en mode par défaut > 2.0
git config --global push.default "simple"
# KDiff3 pour les merges
git config --global merge.tool "kdiff3"
 
# Merge strategy
git config --global merge.ours.driver true
 
# Gestion des CRLF (http://adaptivepatchwork.com/2012/03/01/mind-the-end-of-your-line/)
git config --global core.autocrlf input # uniquement dans Git for Windows
mkdir -p ~/.config/git/
echo '
*       text=auto
*.sh    text eol=lf
*.bat   text eol=crlf' >> ~/.config/git/attributes
```

Configuration specifique
```sh
git config --global user.name ""
git config --global user.email ""
```

## Cygwin 
Dans un Cygwin en administrateur local :
```sh
# Transformation de certains formats (doc, pdf, ...) en texte lors des diff
mkdir -p ~/.config/git/
curl -skL https://github.com/git-for-windows/build-extra/raw/master/git-extra/gitattributes >> /etc/gitattributes
curl -skL https://github.com/git-for-windows/build-extra/raw/master/git-extra/astextplain > /usr/local/bin/astextplain && chmod +x /usr/local/bin/astextplain
curl -skL http://docx2txt.cvs.sourceforge.net/viewvc/docx2txt/docx2txt/docx2txt.pl > /usr/local/bin/docx2txt.pl && chmod +x /usr/local/bin/docx2txt.pl
git config --system diff.astextplain.textconv astextplain
 
# Le bit d'exécution est mal géré sous Windows (même si bien géré dans Cygwin)
# Évite donc à certains outils comme Eclipse, Git for Windows, de jouer avec l'exec-bit
git config --system core.filemode false
 
# Amélioration des perfs
git config --system core.fscache true
```

Dans un Cygwin normal :

```sh
# Transformation de certains formats (doc, pdf, ...) en texte lors des diff
mkdir -p ~/.config/git/
curl -skL https://github.com/git-for-windows/build-extra/raw/master/git-extra/gitattributes >> /etc/gitattributes
curl -skL https://github.com/git-for-windows/build-extra/raw/master/git-extra/astextplain > /usr/local/bin/astextplain && chmod +x /usr/local/bin/astextplain
curl -skL http://docx2txt.cvs.sourceforge.net/viewvc/docx2txt/docx2txt/docx2txt.pl > /usr/local/bin/docx2txt.pl && chmod +x /usr/local/bin/docx2txt.pl
git config --system diff.astextplain.textconv astextplain
 
# Le bit d'exécution est mal géré sous Windows (même si bien géré dans Cygwin)
# Évite donc à certains outils comme Eclipse, Git for Windows, de jouer avec l'exec-bit
git config --system core.filemode false
 
# Amélioration des perfs
git config --system core.fscache true
```