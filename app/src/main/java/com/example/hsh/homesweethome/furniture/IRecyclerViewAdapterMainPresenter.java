package com.example.hsh.homesweethome.furniture;

public interface IRecyclerViewAdapterMainPresenter {
    void onBindFurnitureCategoryAtPosition(int position, RecyclerViewHolderCategoryFurniture viewHolder);
    int getFurnitureCategoriesSize();
    void clearCategories();
}
