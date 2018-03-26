package com.epicest.snippetmanager.object;

import java.io.Serializable;

/**
 * A JavaBeans Object that stores short pieces of text, or snippets
 *
 * @author Jayar
 */
public class Snippet implements Serializable {

 /**
  * The parent of this snippet
  */
 protected Category parent;

 /**
  * The display name of this snippet
  */
 protected String name = "New Snippet";
 /**
  * The text this snippet stores
  */
 protected String snippet = "";

 /**
  * @return the parent of this snippet
  */
 public Category getParent() {
  return parent;
 }

 /**
  * Removes the snippet from it's previous parent and adds itself to a new
  * parent
  *
  * @param newParent the parent to be added to
  */
 public void setParent(Category newParent) {
  removeParent();
  parent = newParent;
  newParent.addSnippet(this);
 }

 /**
  * Removes the snippet from it's previous parent
  */
 void removeParent() {
  if (parent != null) {
   parent.removeSnippet(this);
  }
  parent = null;
 }

 /**
  * @return the name of this snippet
  */
 public String getName() {
  return name;
 }

 /**
  * @return the display name of this snippet
  */
 @Override
 public String toString() {
  return name;
 }

 /**
  * Sets the display name of this snippet
  *
  * @param newName the new display name
  */
 public void setName(String newName) {
  name = newName;
 }

 /**
  * @return the text this snippet stores
  */
 public String getSnippet() {
  return snippet;
 }

 /**
  * Sets the text that this snippet stores
  *
  * @param newSnippet the new snippet
  */
 public void setSnippet(String newSnippet) {
  snippet = newSnippet;
 }

}
