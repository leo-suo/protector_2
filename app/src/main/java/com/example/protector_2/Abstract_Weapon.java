package com.example.protector_2;

abstract class Abstract_Weapon implements Enemy_Interface {
    protected Enemy_Interface tmp;

    public Abstract_Weapon(Enemy_Interface new_Enemy){
        tmp = new_Enemy;
    }

    public int return_attack_damage(){
        return tmp.return_attack_damage();
    }

    public int return_hp(){
        return tmp.return_hp();
    }
}
