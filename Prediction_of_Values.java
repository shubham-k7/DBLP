public class Prediction_of_Values {
	/** correlation Calculated
	 *  @param x
	 *  @param y
	 *  @return correlation of @x,@y
	 */
	private double correlation(double[] x ,double[] y)
	 {
		 double xmean=calc_mean(x);
		 double ymean=calc_mean(y);
		 double xSD=calc_standdarddev(x);
		 double ySD=calc_standdarddev(y);
		 double correlation=0;
		 for(int i=0;i<x.length ;i++)
		   {
		   		correlation+=((x[i]-xmean)*(y[i]-ymean))/x.length/x.length;
		   }
		 correlation/=xSD;
		 correlation/=ySD;
		 correlation*=x.length;
		return correlation;	
	    
	 }
	/** standard dev Calculated
	 *  @param x
	 *  @return standard deiviation of @x
	 */
	 double calc_standdarddev(double[] x)
	 {
	 	double standard_dev=0;
	 	double mean=calc_mean(x);
	 	 for(int i=0;i<x.length ;i++)
		   {
		   		standard_dev+=Math.pow(x[i]-mean, 2);
		   }
		return Math.sqrt(standard_dev/x.length);

	 }
	/** Mean Calculated
	 *  @param x
	 *  @return mean of @x
	 */
	 double calc_mean(double[] x)
	 {
	  double mean=0;
	   for(int i=0;i<x.length ;i++)
	   {
	   		mean+=x[i];
	   }
	   return mean/x.length;
	 }
	 /** Regression slope
	  *   @param x
	  *  @param y
	  * @return regression slope of @x,@y
	     */
	 double regressionslope(double[] x ,double[] y)
	 {
		 return correlation(x, y)*calc_standdarddev(y)/calc_standdarddev(x);
	 }
	 /** Regression Intercept
	     *  @param x
	  *  @param y
	  	*  	@return regression intercept of @x,@y
	     */
	 double regressionintercept(double[] x ,double[] y)
	 {
		return (calc_mean(y)-(regressionslope(x, y)*calc_mean(x)));
	 }
	 /** Predicting Value
	     *  @return predicted value according to value using linear regression
	     */
	 double predict_value(double[] x ,double[] y, double value)
	 {
		return regressionslope(x, y)*value + regressionintercept(x, y);
	 } 

}