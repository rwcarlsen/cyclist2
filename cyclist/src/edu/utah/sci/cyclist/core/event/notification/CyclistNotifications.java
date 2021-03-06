/*******************************************************************************
 * Copyright (c) 2013 SCI Institute, University of Utah.
 * All rights reserved.
 *
 * License for the specific language governing rights and limitations under Permission
 * is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions: The above copyright notice 
 * and this permission notice shall be included in all copies  or substantial portions of the Software. 
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR 
 *  A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Contributors:
 *     Yarden Livnat  
 *******************************************************************************/
package edu.utah.sci.cyclist.core.event.notification;

public class CyclistNotifications {

	public static final String DATASOURCE_FOCUS 		= "datasource_focus";
	public static final String DATASOURCE_ADD 			= "datasource_add";
	public static final String DATASOURCE_REMOVE		= "datasource_remove";
	public static final String DATASOURCE_SELECTED 		= "datasource_selected";
	public static final String DATASOURCE_UNSELECTED 	= "datasource_unselected";
	
	public static final String REMOVE_VIEW				= "remove_view";
	public static final String VIEW_SELECTED			= "view_selected";
	public static final String DUPLICATE_VIEW			= "duplicate_view";
	
	public static final String SHOW_FILTER 				= "show_filter";
	public static final String REMOVE_FILTER 			= "remove_filter";
	public static final String HIDE_FILTER 				= "hide_filter";
	public static final String HIGHLIGHT_FILTER 		= "highlight_filter";
	public static final String UNHIGHLIGHT_FILTER 		= "unhighlight_filter";
	public static final String HIGHLIGHT_FILTERS 		= "highlight_filters";
	public static final String REMOVE_REMOTE_FILTER 	= "remove_remote_filter";
	public static final String ADD_REMOTE_FILTER 		= "add_remote_filter";
	
	public static final String SIMULATION_ADD 			= "simulation_add";
	public static final String SIMULATION_REMOVE 		= "simulation_remove";
	public static final String SIMULATION_SELECTED 		= "simulation_selected";
	public static final String SIMULATION_UNSELECTED 	= "simulation_unselected";
	
	public static final String ADD_TOOL					= "add_tool";
}
