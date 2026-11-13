package codexbuilder.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import codexbuilder.model.options.OptionGroup;

public class ModelFactory {
	
	private static final Map<String, ModelDescription> registry = new HashMap<>();
	
	public static ModelDescription createDescription(
				String   name, 
				int      basePoints,
				StatLine stats,
				Set<UnitType> types,
				Set<OptionGroup> options,
				Set<WargearDescription> defaultGear ){
	return registry.computeIfAbsent(name,
            key -> new ModelDescription(
				name,
				basePoints,
				stats,
				types,
				options,
				defaultGear));
	}
	
	public static ModelDescription createDescription(
				String   name, 
				int      basePoints,
				StatLine stats,
				Set<UnitType> types){
	return registry.computeIfAbsent(name,
            key -> new ModelDescription(
				name,
				basePoints,
				stats,
				types,
				new HashSet<>(),
				new HashSet<WargearDescription>()));
	}
	
	public static ModelDescription createDescription(
			String   name, 
			int      basePoints,
			Set<UnitType> types) {
		return registry.computeIfAbsent(name,
	            key -> new ModelDescription(
					name,
					basePoints,
					types));
	}
	
	public static ModelDescription get(String name) {
		return registry.get(name);
	}
	
	public static List<ModelDescription> getAll(List<String> names){
		List<ModelDescription> models = new ArrayList<>();
		for (String name : names) {
			models.add(get(name));
		}
		return models;
	}
	
	public static Map<String, ModelDescription> getRegistry(){
		return registry;
	}
	
	public static void clearRegistry() {
		registry.clear();
	}
}
