package sheet;

import java.util.ArrayList;
import java.util.Vector;

import XML.Attribute;
import XML.Elements;
import util.BooleanData;
import util.DataTypes;
import util.GenericData;
import util.IntegerData;
import util.StringData;

public class Sequence {
	private String name;
	private int teamCol=0;
	private int matchCol=1;
	private DataTypes[] sequence;
	private String[] names;
	/**
	 * creates the sequence from file
	 * @param root
	 */
	public Sequence(Elements root){
		name=root.getAttribute("name").getValue();
		teamCol=Integer.parseInt(root.getAttribute("team").getValue());
		matchCol=Integer.parseInt(root.getAttribute("match").getValue());
		Vector<DataTypes> types=new Vector<DataTypes>();
		Vector<String> name=new Vector<String>();
		ArrayList<Elements> data=root.getChilds("data");
		for(int i=0;i<data.size();i++){
			types.set(Integer.parseInt(data.get(i).getAttribute("column").getValue()), getType(data.get(i).getAttribute("type").getValue()));
			name.set(Integer.parseInt(data.get(i).getAttribute("column").getValue()), data.get(i).getAttribute("name").getValue());
		}
		sequence=new DataTypes[types.size()];
		names=new String[name.size()];
		for(int i=0;i<types.size();i++){
			sequence[i]=types.get(i);
			names[i]=name.get(i);
		}
	}
	/**
	 * created from editor
	 */
	public Sequence(String name){
		this.name=name;
	}
	private DataTypes getType(String type){
		for(DataTypes t:DataTypes.values()){
			if(t.toString().equals(type)){
				return t;
			}
		}
		return DataTypes.Null;
	}
	public Data parse(Sheet sheet,Data data){
		for(int i=sheet.getHeadRows();i<sheet.getRows();i++){
			String[] row=sheet.get(i);
			String team=row[teamCol];
			String match=row[matchCol];
			Team curTeam;
			if(data.hasTeam(team)){
				curTeam=data.getTeam(team);
			}
			else{
				curTeam=new Team(Integer.parseInt(team), this);
			}
			for(int c=0;c<row.length;c++){
				if(sequence[c]!=DataTypes.Null&&c!=teamCol&&c!=matchCol){
					curTeam.setData(names[c], match, row[c]);
				}
			}
		}
		return data;
	}
	public void sequence(Vector<GenericData> data){
		for(int i=0;i<sequence.length;i++){
			switch(sequence[i]){
			case Boolean:
				data.set(i, new BooleanData(names[i]));
				break;
			case Integer:
				data.set(i, new IntegerData(names[i]));
				break;
			case Null:
				break;
			case String:
				data.set(i, new StringData(names[i]));
				break;
			default:
				break;
			
			}
		}
	}
	public Elements toElements(){
		Elements root=new Elements("sequence");
		root.addAttribute(new Attribute("name",name));
		root.addAttribute(new Attribute("team",""+teamCol));
		root.addAttribute(new Attribute("match",""+matchCol));
		Elements ele;
		for(int i=0;i<sequence.length;i++){
			if(sequence[i]!=DataTypes.Null){
				ele=new Elements("data");
				ele.addAttribute(new Attribute("column",""+i));
				ele.addAttribute(new Attribute("type",sequence[i].toString()));
				ele.addAttribute(new Attribute("name",names[i]));
			}
		}
		
		
		return root;
	}
}
