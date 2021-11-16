package com.flexionmobile.codingchallenge.solution;

import com.flexionmobile.codingchallenge.integration.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchases {

    private List<PurchaseImpl> purchases;

}
