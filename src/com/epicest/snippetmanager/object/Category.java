package com.epicest.snippetmanager.object;

import java.io.Serializable;
import java.util.*;

/**
 * A JavaBeans Object for storing categorized snippets
 *
 * @author Jayar
 */
public class Category implements Serializable {

 /**
  * The display name of this category
  */
 protected String name = "New Category";

 /**
  * The snippets stored in this category
  *
  * Stored as Set to prevent duplicates
  */
 private Set<Snippet> snippets = new HashSet<>();

 /**
  * @return the display name of this category
  */
 public String getName() {
  return name;
 }

 /**
  * Changes the display name of the category
  *
  * @param newName the new display name of this category
  */
 public void setName(String newName) {
  name = newName;
 }

 /**
  * @return stored snippets, in an ArrayList for easy access
  */
 public List<Snippet> getSnippets() {
  return new ArrayList<>(snippets);
 }

 /**
  * @return stored snippets as a array
  */
 public Snippet[] getSnippetsArray() {
  return snippets.toArray(new Snippet[0]);
 }

 /**
  * Changes what snippets are stored in the category
  *
  * @param newSnippets new snippets to be stored
  */
 public void setSnippets(List<Snippet> newSnippets) {
  snippets = new HashSet<>();
  newSnippets.forEach((snippet) -> {
   snippet.setParent(this);
  });
 }

 /**
  * Removes a snippet from the stored snippets
  *
  * @param snippet the snippet to be removed
  * @return whether or not the snippet was removed successfully
  */
 public boolean removeSnippet(Snippet snippet) {
  return snippets.remove(snippet);
 }

 /**
  * Adds a snippet to the stored snippets
  *
  * @param snippet the snippet to be added
  */
 public void addSnippet(Snippet snippet) {
  snippets.add(snippet);
 }

}
