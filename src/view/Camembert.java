package view;

import controller.Controller;
import model.IModel;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

public class Camembert extends JComponent implements MouseListener,IView{

	private IModel model;
	private String titre;
	private Controller controller;
	private Color[] colors = {Color.black,Color.darkGray,Color.lightGray,Color.gray};
	private java.util.List<Arc2D.Double> arcs;
	private int idArc = -1;
	private Ellipse2D.Double circleWhite;
	private int[] xDroite = {480,480,490};
	private int[] yDroite = {50,70,60};
	private Polygon flecheDroite = new Polygon(xDroite,yDroite,3);
	private int[] xGauche = {475,475,465};
	private int[] yGauche = {50,70,60};
	private Polygon flecheGauche = new Polygon(xGauche,yGauche,3);

	public Camembert(IModel model, String titre) {
		this.model = model;
		this.titre = titre;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Dimension size = getSize();

		int compteurItems=0;
		double pourcentage;
		double angleDepart = 0;

		Graphics2D g2d;

		g2d = (Graphics2D) graphics;

		Rectangle window = new Rectangle(0,0,size.width,size.height);
		g2d.setColor(Color.white);
		g2d.fill(window);

		Rectangle bigRectangle= new Rectangle(0,0,500,500);
		Rectangle smallRectangle = new Rectangle(50,50,400,400);

		arcs = new ArrayList<>();
		List<Item> items = model.getItems();
		for (int i = 0, itemsSize = items.size(); i < itemsSize; i++) {
			Item item = items.get(i);
			compteurItems++;
			pourcentage = (double) item.getValue() / (double) model.getTotal();
			Arc2D.Double arc;
			if(i==idArc){
				arc = new Arc2D.Double(bigRectangle, angleDepart, pourcentage * 360. - 1., Arc2D.PIE);
				Rectangle info= new Rectangle(510,20,200,200);
				g2d.setColor(Color.black);
				g2d.draw(info);
				Font font = new Font("Arial", Font.BOLD, 14);
				g2d.setFont(font);
				g2d.drawString(item.getName(), 515,50);
				g2d.drawString("Amount = " +item.getValue(), 515,100);
				g2d.drawString(item.getDescription(), 515,150);

				g2d.setColor(new Color(0x830000));
				g2d.fill(flecheDroite);
				g2d.fill(flecheGauche);


			}
			else {
				arc = new Arc2D.Double(smallRectangle, angleDepart, pourcentage * 360. - 1., Arc2D.PIE);
			}
			g2d.setColor(colors[compteurItems % 4]);
			g2d.fill(arc);
			angleDepart += pourcentage * 360.;
			arcs.add(arc);
		}

		circleWhite = new Ellipse2D.Double(50+smallRectangle.getWidth()/4-60,50+smallRectangle.getHeight()/4-60,smallRectangle.getWidth()/2+120,smallRectangle.getHeight()/2+120);
		g2d.setColor(Color.white);
		g2d.fill(circleWhite);

		Ellipse2D.Double circleBlue = new Ellipse2D.Double(50+smallRectangle.getWidth()/4,50+smallRectangle.getHeight()/4,smallRectangle.getWidth()/2,smallRectangle.getHeight()/2);
		g2d.setColor(new Color(24, 0, 122));
		g2d.fill(circleBlue);

		drawCenteredTitle(g2d,titre,model.getTotal(),smallRectangle);


	}
	private void drawCenteredTitle(Graphics g, String title, int totalAmount, Rectangle rect) {
		Font font = new Font("Arial", Font.BOLD, 14);
		FontMetrics metrics = g.getFontMetrics(font);

		int x = 50+ (rect.width - metrics.stringWidth(title)) / 2;
		int y = 50+ ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);
		g.setColor(Color.white);

		g.drawString(title, x, y);

		x = 50+ (rect.width - metrics.stringWidth(totalAmount+" Euros")) / 2;
		g.drawString(totalAmount+" Euros",x,y+30);
	}

	@Override
	public void notifyChange(){
		titre=model.getName();
		repaint();
	}

	@Override
	public void setController(Controller controller) {
		this.controller=controller;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {

	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		boolean click=false;
		for (int i=0; i<arcs.size();i++){
			Arc2D.Double arc = arcs.get(i);
			if(arc.contains(mouseEvent.getX(),mouseEvent.getY()) && !circleWhite.contains(mouseEvent.getX(),mouseEvent.getY())){
				idArc=i;
				click=true;

			}
		}
		if(flecheDroite.contains(mouseEvent.getX(),mouseEvent.getY())){
			idArc = (idArc -1 + arcs.size()) % arcs.size();
			click = true;
		}
		if(flecheGauche.contains(mouseEvent.getX(),mouseEvent.getY())){
			idArc= (idArc +1) % arcs.size();
			click = true;
		}

		if(!click){
			idArc=-1;
		}
		controller.onClickEvent();
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}
}
