(ns insert-randomly.core)

(defn insert-result-randomly
  "insert the results of calling `f` in `n` random positions in `xs`.
  returns a vector of size (+ n (count xs))"
  ([xs f] (insert-result-randomly xs 1 f))
  ([xs n f]
   (let [xs (vec xs)]
     (loop [xs xs
            n n
            k (count xs)]
       (if (zero? n)
         xs
         (let [r (-> k inc rand-int)]
           (recur (into (conj (subvec xs 0 r) (f)) (subvec xs r))
                  (dec n)
                  (inc k))))))))

(defn insert-randomly
  "insert the value `x` at `n` random positions in `xs`.
  returns a vector of size (+ n (count xs)).
  the same as (insert-result-randomly (constantly x))"
  ([xs x] (insert-randomly xs 1 x))
  ([xs n x] (insert-result-randomly xs n (constantly x))))

(defn update-randomly
  "call `update` with `f` at `n` random positions in `xs`.
  Works for both vetcors and maps.
  If `xs` is a map then f is applied to the values of `n` random keys.
  Never uses the same index or key twice."
  ([xs f] (update-randomly xs 1 f))
  ([xs n f]
   (reduce (fn [xs k] (update xs k f))
           xs
           (take n (shuffle (if (map? xs) (keys xs) (range (count xs))))))))

(defn replace-randomly
  "Replace the values at `n` random indicies with `x` in `xs`.
  If `xs` is a map, replaces `n` values with `x`
  Never users the same key or index twice."
  ([xs x] (replace-randomly xs 1 x))
  ([xs n x] (update-randomly xs n (constantly x))))

;; (defn update-randomly
;;   "call `update` with `f` and any supplied `args` at `n` random positions in `xs`.
;;   Works for both vetcors and maps.
;;   If `xs` is a map then f is applied to the values of `n` random keys.
;;   Never uses the same index or key twice."
;;   [xs n f & args]
;;   (reduce (fn [xs k] (apply update xs k f args))
;;           xs
;;           (take n (shuffle (if (map? xs) (keys xs) (range (count xs)))))))
