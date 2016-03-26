/**
 * CustomResourceBundle.java
 *
 */
package com.inclever.library.resourcebundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * This custom resource bundle manages different resource bundles. Its used for
 * localization
 *
 * @author Ashish Panchal(aashish.panchal@gmail.com)
 */
public class CustomResourceBundle extends ResourceBundle {

    /**
     * Holds the base name
     */
    protected String[] baseName;

    /**
     * The resource instance
     */
    private static CustomResourceBundle instance = null;

    /**
     * Holds the lists of bundles
     */
    private static ArrayList<ResourceBundle> bundles = new ArrayList<>();

    /**
     * 
     */
    public CustomResourceBundle() {
        this(new String[0]);
    }

    /**
     * Sets the resource bundle base names as an array from a string like:
     * test1,test2 etc or test1 test2 etc
     */
    protected CustomResourceBundle(String baseName) {
        buildBaseName(baseName, ",");
    }

    /**
     * @return a resource bundle
     */
    public static ResourceBundle getBundle() {
        if (instance == null)
            instance = new CustomResourceBundle();
        return instance;
    }

    /**
     * @return an array of all resource bundle base names
     */
    public String[] getBaseName() {
        return baseName;
    }

    /**
     * Adds a resource bundle to the collection of bundles
     *
     * @param bundle
     *            the ResourceBundle to add
     */
    public static void addResourceBundle(ResourceBundle bundle) {
        bundles.add(bundle);
    }

    /**
     * Removes a resource bundle from the collection of bundles
     *
     * @param bundle
     *            the ResourceBundle to remove
     */
    public static void removeResourceBundle(ResourceBundle bundle) {
        bundles.remove(bundle);
    }

    /**
     * Sets the resource bundle base names as an array
     */
    protected CustomResourceBundle(String[] baseName) {
        this.baseName = baseName;
    }

    /**
     * Builds the resource bundle base names as an array from a string like:
     * test1,test2 etc or test1 test2 etc
     */
    protected void buildBaseName(String base, String delim) {
        String s = null;
        try {
            s = System.getProperty(base);
            if (s == null)
                return;
            StringTokenizer st = new StringTokenizer(s, delim);
            baseName = new String[st.countTokens()];
            int i = 0;
            while (st.hasMoreTokens())
                baseName[i++] = st.nextToken().trim();
        } catch (Exception e) {
            throw new RuntimeException("Can not resolve base name: " + s);
        }
    }

    /**
     * Overirdes the getKey to go through all the resource bundles and returns
     * an eneumeration
     *
     * @return Enumeration Enumeration of keys
     */
    @Override
    public Enumeration<String> getKeys() {
        return new Enumeration<String>() {
            Enumeration<String> e = null;
            int i = 0;

            public boolean hasMoreElements() {
                boolean b = false;
                while (e == null || !(b = e.hasMoreElements())) {
                    if (i >= bundles.size()) {
                        e = null;
                        return b;
                    }
                    e = bundles.get(i++).getKeys();
                }
                return b;
            }

            @Override
            public String nextElement() {
                if (e == null)
                    throw new NoSuchElementException();
                return e.nextElement();
            }
        };
    }

    /**
     * Gets the get an object from resource bundles from a particular key
     *
     * @param key
     *            The resource bundle Key
     */
    @Override
    protected Object handleGetObject(String key) {
        ResourceBundle rb;
        String val = null;
        for (int i = 0; i < bundles.size(); i++) {
            rb = bundles.get(i);
            try {
                val = rb.getString(key);
            } catch (Exception e) {
                // only will happen when
                // we find nothing in the current bundle now
                // move onto the next
            }
            if (val != null) {
                break;
            }
        }
        return val;
    }
}
