package at.hochbichler.java8.lambdadesign;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AssetUtil {
    private static final List<Asset> assets = Arrays.asList(
            new Asset(Asset.AssetType.BOND, 1000),
            new Asset(Asset.AssetType.BOND, 2000),
            new Asset(Asset.AssetType.STOCK, 3000),
            new Asset(Asset.AssetType.STOCK, 4000)
    );

    public static int totalAssetValues(final List<Asset> assets, final Predicate<Asset> assetSelector) {
        return assets.stream()
                .filter(assetSelector)
                .mapToInt(Asset::getValue)
                .sum();

    }

    public static void main(String[] args) {
        System.out.println("Sum All: " + totalAssetValues(assets, asset -> true));
        System.out.println("Sum Bonds: " + totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND));
        System.out.println("Sum Stocks: " + totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.STOCK));
    }
}
