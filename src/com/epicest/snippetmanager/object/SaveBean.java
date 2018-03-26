package com.epicest.snippetmanager.object;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A JavaBeans Object that stores information
 * <p>
 * A JavaBeans Object that stores all information needed to run this project
 * seamlessly after consecutive program restarts or reloads.
 *
 * @author Jayar
 */
public class SaveBean implements Serializable {

 /**
  * The bulk of what is stored
  */
 public ArrayList<Category> content = null;

 /**
  * Whether lines are numbered in the snippet editor
  */
 public boolean lineNumbers = false;
 /**
  * Whether the snippet editor wraps or not
  */
 public boolean wrap = false;
 /**
  * Whether the snippet editor wraps by word or by character
  */
 public boolean wrapByWords = true;

 /**
  * Whether the program saves on exit/closing
  */
 public boolean saveAtClose = true;

 /**
  * @return saved categories, with their snippets.
  */
 public ArrayList<Category> getContent() {
  return content;
 }

 /**
  * Sets the saved or to-be-saved categories, with their snippets
  *
  * @param newContent the saved or to-be-saved categories
  */
 public void setContent(ArrayList<Category> newContent) {
  this.content = newContent;
 }

 /**
  * @return whether lines are numbered in the snippet editor
  */
 public boolean isLineNumbering() {
  return lineNumbers;
 }

 /**
  * Sets whether or not to number the lines in the editor
  *
  * @param newLineNumbers whether or not to number the lines in the editor
  */
 public void setLineNumbering(boolean newLineNumbers) {
  lineNumbers = newLineNumbers;
 }

 /**
  * @return whether the snippet editor wraps or not
  */
 public boolean isWrapping() {
  return wrap;
 }

 /**
  * Sets whether or not to wrap lines in the editor
  *
  * @param newWrap whether or not to wrap lines in the editor
  */
 public void setWrapping(boolean newWrap) {
  wrap = newWrap;
 }

 /**
  * @return whether the snippet editor wraps by word or by character
  */
 public boolean isWrapByWords() {
  return wrapByWords;
 }

 /**
  * Sets whether or not to wrap the lines by word in the editor
  *
  * @param newWrapByWords whether or not to wrap the lines by word in the editor
  */
 public void setWrapByWords(boolean newWrapByWords) {
  wrapByWords = newWrapByWords;
 }

 /**
  * @return whether the program saves on exit/closing
  */
 public boolean isSavingAtClose() {
  return saveAtClose;
 }

 /**
  * Sets whether the program saves on exit/closing
  *
  * @param newSaveAtClose whether the program saves on exit/closing
  */
 public void setSavingAtClose(boolean newSaveAtClose) {
  saveAtClose = newSaveAtClose;
 }

}
