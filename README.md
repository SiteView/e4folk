e4-rendering
============
e4-rendering is a proof of concept for providing new rendering engines
for the Eclipse 4 Application platform. I provide engines for
* JavaFX 2.1
* Swing
* SWT (alternative renderer to prove that the generic concepts are working with SWT)

Setup
-----
* Install JavaFX (SDK or Runtime) for your platform 
* Install e(fx)clipse for your platform, see http://efxclipse.org/install.html#for-the-adventurous: 
* Install eclipse 4.2M6 SDK
* Install http://download.eclipse.org/modeling/tmf/xtext/updates/composite/latest/ into 4.2m6
* Install http://www.efxclipse.org/p2-repos/releases/latest/ into 4.2m6
* Open the Eclipse SDK and set the JavaFX SDK location in the prefences
* Restart Eclipse
* Import all projects from this git repo
* Use the predefined run configurations (using contacts.product under each com.toedter.e4.demo.contact.javafx ) to launch the JavaFX, similar for Swing and SWT demos.  Under contacts.product, "Dependencies", clicks on "Add Required Plugin", go to "Overview" tag, launch

