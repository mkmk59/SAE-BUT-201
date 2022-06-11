package com.example.sae_robots_mineur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    protected Robot monRobot;

    @BeforeEach
    void init() throws Exception{
        monRobot=new Robot(1,null);
        monRobot.setSpecialite(Robot.Specialite.Or);
    }

    @Test
    void test_deposer1() {
        monRobot.setNombre_action(1);
        assertEquals(false,monRobot.deposer(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_deposer2(){
        monRobot.setNombre_action(0);
        assertNull(monRobot.getParcelle());
        assertEquals(false,monRobot.deposer(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_deposer3(){
        Parcelle parcelle = new Parcelle(0,0,null);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.TERRAIN_VIDE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.deposer(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }


    @Test
    void test_deposer4(){
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.deposer(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_deposer5(){
        Parcelle parcelle = new Parcelle(0,0,null);
        Entrepot entrepot = new Entrepot(1, Robot.Specialite.Or);
        entrepot.setParcelle(parcelle);
        entrepot.setCapacite_stockage(100);
        parcelle.setLieu(entrepot);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.ENTREPOT,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.deposer(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
        assertEquals(0,((Entrepot)monRobot.getParcelle().getLieu()).getQuantite_minerai_actuelle());
    }

    @Test
    void test_deposer6(){
        Parcelle parcelle = new Parcelle(0,0,null);
        Entrepot entrepot = new Entrepot(1, Robot.Specialite.Ni);
        entrepot.setParcelle(parcelle);
        entrepot.setCapacite_stockage(100);
        parcelle.setLieu(entrepot);
        monRobot.setParcelle(parcelle,true);
        monRobot.setQuantite_minerai(25);
        assertEquals(Lieu.Type_Lieu.ENTREPOT,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.deposer(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(25,monRobot.getQuantite_minerai());
        assertEquals(0,((Entrepot)monRobot.getParcelle().getLieu()).getQuantite_minerai_actuelle());
    }

    @Test
    void test_deposer7(){
        Parcelle parcelle = new Parcelle(0,0,null);
        Entrepot entrepot = new Entrepot(1, Robot.Specialite.Or);
        entrepot.setParcelle(parcelle);
        entrepot.setCapacite_stockage(100);
        entrepot.setQuantite_minerai_actuelle(100);
        parcelle.setLieu(entrepot);
        monRobot.setParcelle(parcelle,true);
        monRobot.setQuantite_minerai(25);
        assertEquals(Lieu.Type_Lieu.ENTREPOT,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.deposer(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(25,monRobot.getQuantite_minerai());
        assertEquals(100,((Entrepot)monRobot.getParcelle().getLieu()).getQuantite_minerai_actuelle());
    }

    @Test
    void test_deposer8(){
        Parcelle parcelle = new Parcelle(0,0,null);
        Entrepot entrepot = new Entrepot(1, Robot.Specialite.Or);
        entrepot.setParcelle(parcelle);
        entrepot.setCapacite_stockage(100);
        entrepot.setQuantite_minerai_actuelle(90);
        parcelle.setLieu(entrepot);
        monRobot.setParcelle(parcelle,true);
        monRobot.setQuantite_minerai(25);
        assertEquals(Lieu.Type_Lieu.ENTREPOT,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(true,monRobot.deposer(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(15,monRobot.getQuantite_minerai());
        assertEquals(100,((Entrepot)monRobot.getParcelle().getLieu()).getQuantite_minerai_actuelle());
    }

    @Test
    void test_deposer9(){
        Parcelle parcelle = new Parcelle(0,0,null);
        Entrepot entrepot = new Entrepot(1, Robot.Specialite.Or);
        entrepot.setParcelle(parcelle);
        entrepot.setCapacite_stockage(100);
        entrepot.setQuantite_minerai_actuelle(75);
        parcelle.setLieu(entrepot);
        monRobot.setParcelle(parcelle,true);
        monRobot.setQuantite_minerai(25);
        assertEquals(Lieu.Type_Lieu.ENTREPOT,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(true,monRobot.deposer(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
        assertEquals(100,((Entrepot)monRobot.getParcelle().getLieu()).getQuantite_minerai_actuelle());
    }


    @Test
    void test_collecter1() {
        monRobot.setNombre_action(1);
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        assertEquals(false,monRobot.collecter(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_collecter2() {
        monRobot.setQuantite_minerai(20);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        assertEquals(false,monRobot.collecter(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(20,monRobot.getQuantite_minerai());
    }

    @Test
    void test_collecter3() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        assertEquals(false,monRobot.collecter(true));
        assertNull(monRobot.getParcelle());
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_collecter4() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.TERRAIN_VIDE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.collecter(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_collecter5() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Entrepot entrepot = new Entrepot(1, Robot.Specialite.Or);
        entrepot.setParcelle(parcelle);
        parcelle.setLieu(entrepot);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.ENTREPOT,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.collecter(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
    }

    @Test
    void test_collecter6() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setQuantite_minerai_restant(100);
        mine.setSpecialite(Robot.Specialite.Ni);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.collecter(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
        assertEquals(100,((Mine)monRobot.getParcelle().getLieu()).getQuantite_minerai_restant());
    }

    @Test
    void test_collecter7() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setQuantite_minerai_restant(0);
        mine.setSpecialite(Robot.Specialite.Or);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(false,monRobot.collecter(true));
        assertEquals(0,monRobot.getNombre_action());
        assertEquals(0,monRobot.getQuantite_minerai());
        assertEquals(0,((Mine)monRobot.getParcelle().getLieu()).getQuantite_minerai_restant());
    }

    @Test
    void test_collecter8() {
        monRobot.setQuantite_minerai(15);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setQuantite_minerai_restant(100);
        mine.setSpecialite(Robot.Specialite.Or);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(true,monRobot.collecter(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(20,monRobot.getQuantite_minerai());
        assertEquals(95,((Mine)monRobot.getParcelle().getLieu()).getQuantite_minerai_restant());
    }

    @Test
    void test_collecter9() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setQuantite_minerai_restant(100);
        mine.setSpecialite(Robot.Specialite.Or);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(true,monRobot.collecter(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(10,monRobot.getQuantite_minerai());
        assertEquals(90,((Mine)monRobot.getParcelle().getLieu()).getQuantite_minerai_restant());
    }

    @Test
    void test_collecter10() {
        monRobot.setQuantite_minerai(16);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setQuantite_minerai_restant(5);
        mine.setSpecialite(Robot.Specialite.Or);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(true,monRobot.collecter(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(20,monRobot.getQuantite_minerai());
        assertEquals(1,((Mine)monRobot.getParcelle().getLieu()).getQuantite_minerai_restant());
    }

    @Test
    void test_collecter11() {
        monRobot.setQuantite_minerai(0);
        monRobot.setCapacite_minerai_max(20);
        monRobot.setCapacite_extraction(10);
        Parcelle parcelle = new Parcelle(0,0,null);
        Mine mine = new Mine(1);
        mine.setQuantite_minerai_restant(5);
        mine.setSpecialite(Robot.Specialite.Or);
        mine.setParcelle(parcelle);
        parcelle.setLieu(mine);
        monRobot.setParcelle(parcelle,true);
        assertEquals(Lieu.Type_Lieu.MINE,monRobot.getParcelle().getLieu().getType_Lieu());
        assertEquals(true,monRobot.collecter(true));
        assertEquals(1,monRobot.getNombre_action());
        assertEquals(5,monRobot.getQuantite_minerai());
        assertEquals(0,((Mine)monRobot.getParcelle().getLieu()).getQuantite_minerai_restant());
    }

}