package BeyondTheBaisic.Buildings

class Building<out T: BaseBuildingMaterial>(val buildingMaterial: T){
    val baseMaterialsNeeded = 100
    val actualMaterialsNeeded = buildingMaterial.numberNeeded * baseMaterialsNeeded

    fun build(){
        println("$actualMaterialsNeeded ${buildingMaterial::class.simpleName} required")
    }
}

fun main() {
    Building(Wood()).build()
    isSmallBuilding(Building(Wood()))
}

fun <T: BaseBuildingMaterial> isSmallBuilding(building: Building<T>){
    if (building.actualMaterialsNeeded <  500)
        println("small building")
    else
        println("large building")
}