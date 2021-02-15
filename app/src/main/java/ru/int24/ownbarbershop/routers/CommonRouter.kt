package ru.int24.ownbarbershop.routers


class CommonRouter {

}


//nterface ScreenRouterLogic{
//    fun routeHomeFragmentToScanFragment(docUID:String)
//    fun routeTradeFragmentToScanFragment(docUID:String)
//}
//
//class ScreenRouter(private val fragment: Fragment): ScreenRouterLogic {
//
//    private val key:String = "ru.int24.scandm.routers.docUID"
//
//    override fun routeHomeFragmentToScanFragment(docUID: String) {
//        val bundle = bundleOf(key to docUID)
//        fragment.apply {
//            findNavController()
//                    .navigate(R.id.action_homeFragment_to_navigation, bundle)
//        }
//    }
//
//    override fun routeTradeFragmentToScanFragment(docUID: String) {
//        val bundle = bundleOf(key to docUID)
//        fragment.apply {
//            findNavController()
//                    .navigate(R.id.action_tradeFragment_to_navigation, bundle)
//        }
//    }
//}