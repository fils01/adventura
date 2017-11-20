/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import logika.Inventar;

/**
 *
 * @author sf
 */
public interface SubjectInventar {
    
    void registerObserver(ObserverInventar observer);
    
    void removeObserver(ObserverInventar observer);
    
    void notifyObservers();

}
