package com.example.protector_2;

public class Decorator_Armor extends Abstract_Weapon{
    public Decorator_Armor(Enemy_Interface new_Enemy){
        super(new_Enemy);
    }

    public int return_attack_damage(){
        return tmp.return_attack_damage() + 5;
    }

    public int return_hp(){
        return tmp.return_hp() + 150;
    }
}
