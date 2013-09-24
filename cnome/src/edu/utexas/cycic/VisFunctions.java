package edu.utexas.cycic;

import java.util.ArrayList;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;

/**
 * Contains all of the generic and misc visualization functions 
 * for the CYCIC fuel cycle simulator.
 * @author Robert
 *
 */
public class VisFunctions {
	/**
	 * Converts a string to a color.
	 * @param string String to be converted.
	 * @return rbg triple stored in an ArrayList<Integer>
	 */
	public static ArrayList<Integer> stringToColor(String string){
		String hashCode;
		int red=0;
		int blue=0;
		int green=0;
		int p=0;
		ArrayList<Integer> rgbArray = new ArrayList<Integer>();
		
		hashCode = Integer.toString(Math.abs(string.hashCode()));
		if(hashCode.length()>(p+3)){
			red=Integer.parseInt(hashCode.substring(p,p+3));
			p+=3;
			if(hashCode.length()>(p+3)){
				green=Integer.parseInt(hashCode.substring(p,p+3));
				p+=3;
				if(hashCode.length()>(p+3)){
					blue=Integer.parseInt(hashCode.substring(p,p+3));
					p+=3;
				}else{
					blue=Integer.parseInt(hashCode.substring(p,hashCode.length()));
				}	
			}else{
				green=Integer.parseInt(hashCode.substring(p,hashCode.length()));
			}
		}else{
			red=Integer.parseInt(hashCode);
		}
		
		while (red>255) {
			red-=256;
		}
		while (green>255) {
			green-=256;
		}
		while (blue>255) {
			blue-=256;
		}
		
		rgbArray.add(red);
		rgbArray.add(green);
		rgbArray.add(blue);
		return rgbArray;
	}
	
	/**
	 * Test to determine which font color to use, based on the color
	 * of the node the text belongs to.
	 * @param array rbg color ArrayList<Integer>
	 * @return
	 */
	public static boolean colorTest(ArrayList<Integer> array){
		int tally = 0;
		for(int i = 0; i < array.size(); i++){
			if(array.get(i) < 80){
				tally +=1;
			}
		}
		if(tally > 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Test determines if the a color should be made darker or lighter
	 * based on the initial color being used.
	 * @param color rbg Integer to test.
	 * @return
	 */
	public static double colorMultiplierTest(Integer color){
		if(color < 185){
			return 1.3;
		}else{
			return 0.7;
		}
	}
	
	/**
	 * Adjusts the color of a node when highlighted
	 */
	static ColorAdjust colorAdjust = new ColorAdjust(){
		{
			setBrightness(0.2);
			setHue(-0.05);
		}
	};
	
	/**
	 * Depricated
	 */
	static Bloom bloom = new Bloom(){
		{
			setThreshold(1.0);
		}
	};
	
	/**
	 * Code used to link a facilityCircle and a marketCircle in the CYCIC pane.
	 * @param source facilityCircle that starts the line.
	 * @param target marketCircle that ends the line.
	 */
	static void linkNodesSimple(FacilityCircle source, MarketCircle target){
		MarketCircle markIndex = null;
		boolean hiddenLinkCheck = false;
		nodeLink link = new nodeLink();
		link.source = source;
		link.target = target;
		link.line.setStartX(source.getCenterX());
		link.line.setStartY(source.getCenterY());
		link.line.setEndX(target.getCenterX());
		link.line.setEndY(target.getCenterY());

		for(int i = 0; i < CycicScenarios.workingCycicScenario.marketNodes.size(); i++){
			if(CycicScenarios.workingCycicScenario.marketNodes.get(i) == target){
				markIndex = CycicScenarios.workingCycicScenario.marketNodes.get(i);
			}
		}
		// Adding the Parent to childMarket link //
		if(CycicScenarios.workingCycicScenario.hiddenLinks.size() == 0){
			addHiddenLink(CycicScenarios.workingCycicScenario.FacilityNodes.get(source.parentIndex).cycicCircle, markIndex);
		}
		for(int n = 0; n < CycicScenarios.workingCycicScenario.hiddenLinks.size(); n++){
			if( (MarketCircle) CycicScenarios.workingCycicScenario.hiddenLinks.get(n).target == target 
				&& CycicScenarios.workingCycicScenario.hiddenLinks.get(n).source == CycicScenarios.workingCycicScenario.FacilityNodes.get(source.parentIndex).cycicCircle){
				hiddenLinkCheck = true;
			}
		} 
		if ( hiddenLinkCheck == false) {
			addHiddenLink(CycicScenarios.workingCycicScenario.FacilityNodes.get(source.parentIndex).cycicCircle, markIndex);
		}
		CycicScenarios.workingCycicScenario.Links.add(link);
		Cycic.pane.getChildren().addAll(link.line);
		link.line.toBack();
	}
	/**
	 * Adds a link between a prototype facilityCircle and a marketCircle.
	 * @param parent facilityCircle of the prototype.
	 * @param market marketCircle for the market.
	 */
	static void addHiddenLink(FacilityCircle parent, MarketCircle market){
		nodeLink hiddenLink = new nodeLink();
		hiddenLink.source = parent;
		hiddenLink.target = market;
		hiddenLink.line.setStartX(parent.getCenterX());
		hiddenLink.line.setStartY(parent.getCenterY());
		hiddenLink.line.setEndX(market.getCenterX());
		hiddenLink.line.setEndY(market.getCenterY());
		CycicScenarios.workingCycicScenario.hiddenLinks.add(hiddenLink);
		if(parent.childrenShow == false){
			Cycic.pane.getChildren().addAll(hiddenLink.line);
			hiddenLink.line.toBack();
		}
	}
	
	/**
	 * Reloads the CYCIC pane when changes have been made to it's contents.
	 */
	static void reloadPane(){
		Cycic.pane.getChildren().remove(0, Cycic.pane.getChildren().size());
		for(int i = 0; i < CycicScenarios.workingCycicScenario.FacilityNodes.size(); i++){
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle);
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.menu);
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.text);
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.image);
			if(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenShow == false){
				for(int ii = 0; ii < CycicScenarios.workingCycicScenario.hiddenLinks.size(); ii++){
					if(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle == CycicScenarios.workingCycicScenario.hiddenLinks.get(ii).source){
						Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.hiddenLinks.get(ii).line);
						CycicScenarios.workingCycicScenario.hiddenLinks.get(ii).line.toBack();
					}
				}
			}else{
				for(int ii = 0; ii < CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenList.size(); ii++){
					Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenList.get(ii));
					Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenList.get(ii).menu);
					Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenList.get(ii).text);
					Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenList.get(ii).image);
					for(int n = 0; n < CycicScenarios.workingCycicScenario.Links.size(); n++){
						if(CycicScenarios.workingCycicScenario.Links.get(n).source == CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenList.get(ii)){
							Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.Links.get(n).line);
							CycicScenarios.workingCycicScenario.Links.get(n).line.toBack();
						}
					}
				}
				for(int n = 0; n < CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenLinks.size(); n++){
					Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenLinks.get(n).line);
					CycicScenarios.workingCycicScenario.FacilityNodes.get(i).cycicCircle.childrenLinks.get(n).line.toBack();
				}
			}
		}
		for(int i = 0; i < CycicScenarios.workingCycicScenario.marketNodes.size(); i++){
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.marketNodes.get(i));
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.marketNodes.get(i).menu);
			Cycic.pane.getChildren().add(CycicScenarios.workingCycicScenario.marketNodes.get(i).text);			
		}
	}
	
	/**
	 * Tests for the presence of a hiddenLink to see if it can be removed 
	 * or if it is still required after a commodities has changed.
	 * @param parentName facilityCircle of the parent
	 * @param oldMarket marketCircle that is removed from the facility
	 * circle's commodity list.
	 */
	public static void hiddenLinkTest(FacilityCircle parentName, Object oldMarket){
		int hiddenLinkCount = 0;
		for (int j = 0; j < CycicScenarios.workingCycicScenario.hiddenLinks.size(); j++){
			if(CycicScenarios.workingCycicScenario.hiddenLinks.get(j).source == parentName && CycicScenarios.workingCycicScenario.hiddenLinks.get(j).target == oldMarket){
				hiddenLinkCount += 1;
			}
			if (hiddenLinkCount > 1){
				CycicScenarios.workingCycicScenario.hiddenLinks.remove(j);
				j = j - 1;
			}
		}
	}
	
	/**
	 * Removing a hiddenLink after a facilities commodity has been changed.
	 * @param parentName facilityCircle at the start of the hiddenLink.
	 * @param oldMarket marketCircle at the end of the hiddenLink.
	 * @param test Boolean return of the hiddenLinkTest.
	 */
	public static void hiddenLinkRemoval(FacilityCircle parentName, Object oldMarket, Boolean test){

		for (int j = 0; j < CycicScenarios.workingCycicScenario.hiddenLinks.size(); j++){
			if(CycicScenarios.workingCycicScenario.hiddenLinks.get(j).source == parentName && CycicScenarios.workingCycicScenario.hiddenLinks.get(j).target == (MarketCircle) oldMarket){
				if (test == false){
					CycicScenarios.workingCycicScenario.hiddenLinks.remove(j);
					j = j - 1;
				}
			}
		}
	}
}